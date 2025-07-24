package com.cigna.benefit.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cigna.benefit.repository.HealthPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class KafkaConsumer {

    @Autowired
    private HealthPlansRepository healthPlan_repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Integer id;

    @KafkaListener(topics = "benefit-events", groupId = "health-bene-group")
    public void consume(String message) {
        try {
            //HealthPlans healthPlans = objectMapper.readValue(message, HealthPlans.class);
            System.out.println("JSON Msg: "+message);
            JsonNode rootNode=objectMapper.readTree(message);
            Integer id=rootNode.get("id").asInt();

            JsonNode fieldsNode = rootNode.get("fields");

            if (fieldsNode == null || !fieldsNode.isObject()) {
                System.out.println("No 'fields' node or it's not a JSON object");
                return;
            }

            Update update = new Update();
            Iterator<Map.Entry<String, JsonNode>> fields=fieldsNode.fields();
            while (fields.hasNext()) {
                System.out.println("Inside while loop");
                Map.Entry<String, JsonNode> entry = fields.next();
                System.out.println("Updating field "+entry.getKey()+ " with value :"+entry.getValue());
                update.set(entry.getKey(), entry.getValue().asText()); // or asText(), asInt(), etc.
            }

            Query query = new Query(Criteria.where("_id").is(id));
            mongoTemplate.updateFirst(query, update, "health_plans");
            System.out.println("Updated fields for ID " + id);
            System.out.println("JSON received " + message);

            mongoTemplate.insert(objectMapper.convertValue(rootNode, Map.class), "benefit-events");

            System.out.println("Original event inserted into 'benefit-events' collection");
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
