package com.POC.ProducerDataBase.Config;





import com.POC.ProducerDataBase.Model.Customer;
import com.POC.ProducerDataBase.Service.KafkaProducerService;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@Configuration
public class MongoChangeStreamListener {

    private final MongoClient mongoClient;
    private final MongoTemplate mongoTemplate;
    private final KafkaProducerService kafkaProducerService;

    public MongoChangeStreamListener(MongoClient mongoClient,
                                     MongoTemplate mongoTemplate,
                                     KafkaProducerService kafkaProducerService) {
        this.mongoClient = mongoClient;
        this.mongoTemplate = mongoTemplate;
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostConstruct
    public void startWatching() {
        System.out.println(" ====***Change stream listener initialized***===");

        MongoDatabase db = mongoClient.getDatabase("cignapoc");
        MongoCollection<Document> benefitEventsCollection = db.getCollection("benefit-events");
        MongoCollection<Document> customerHealthPlansCollection = db.getCollection("customer_health_plan");

        new Thread(() -> {
            benefitEventsCollection.watch()
                    .fullDocument(FullDocument.UPDATE_LOOKUP)
                    .forEach((ChangeStreamDocument<Document> change) -> {
                        System.out.println("Change detected: " + change.getOperationType());
                        System.out.println("Changed document: " + change.getFullDocument());

                        String operation = change.getOperationType().getValue();
                        Document fullDoc = change.getFullDocument();
                        Object planEventId = change.getDocumentKey().get("_id");

                        Document message = new Document()
                                .append("event_type", operation)
                                .append("benefit_event_id", planEventId);

                        if ("update".equals(operation)) {
                            message.append("changed_fields", change.getUpdateDescription().getUpdatedFields());
                        }

                        if (fullDoc != null) {
                            message.append("benefit_event_data", fullDoc);

                            Integer planId = fullDoc.getInteger("id");
                            if (planId != null) {
                                // 1. Find all health plan docs matching this plan_id
                                FindIterable<Document> customerPlans = customerHealthPlansCollection.find(Filters.eq("plan_id", planId));

                                List<Document> customerDataList = new ArrayList<>();
                                ObjectMapper mapper = new ObjectMapper();

                                for (Document customerPlanDoc : customerPlans) {
                                    message.append("customer_health_plan", customerPlanDoc);

                                    Integer customerId = customerPlanDoc.getInteger("customer_id");
                                    if (customerId != null) {
                                        Customer customer = mongoTemplate.findById(customerId, Customer.class, "customers");

                                        if (customer != null) {
                                            Map<String, Object> customerMap = mapper.convertValue(customer, Map.class);
                                            Document combined = new Document()
                                                    .append("customer_id", customerId)
                                                    .append("customer_data", customerMap);
                                            customerDataList.add(combined);
                                            System.out.println("Fetched customer for plan ID " + planId + ": " + customerMap);
                                        } else {
                                            System.out.println("No customer found for ID: " + customerId);
                                        }
                                    }
                                }

                                message.append("associated_customers", customerDataList);
                            } else {
                                System.out.println("No 'id' field found in benefit event document.");
                            }
                        }

                        if ("delete".equals(operation)) {
                            message.append("deleted", true);
                        }

                        kafkaProducerService.sendEvent(message.toJson());
                    });
        }).start();
    }
}


