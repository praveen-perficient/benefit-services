package com.poc.mongodb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.mongodb.model.HealthPlans;
import com.poc.mongodb.repository.HealthPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class KafkaConsumer {

    @Autowired
    private HealthPlansRepository healthPlan_repo;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "benefit-events", groupId = "health-bene-group")
    public void consume(String message) {
        try {
            //HealthPlans healthPlans = objectMapper.readValue(message, HealthPlans.class);
            JsonNode rootNode=objectMapper.readTree(message);
            Iterator<Map.Entry<String, JsonNode>> fields=rootNode.fields();
            while(fields.hasNext()){
                Map.Entry<String, JsonNode> field= fields.next();
                if(field.getKey().equalsIgnoreCase("fields")){
                    JsonNode value=field.getValue();
                    Iterator<Map.Entry<String, JsonNode>> innerFields=value.fields();
                    while(innerFields.hasNext()){
                        Map.Entry<String, JsonNode> innerField= innerFields.next();
                        System.out.println("Key: "+innerField.getKey()+" Value: "+innerField.getValue());
                    }
                }
                System.out.println("Key: "+field.getKey()+" Value: "+field.getValue());
            }
            //healthPlan_repo.save(healthPlans);
            //System.out.println("Saved to MongoDB: " + healthPlans);
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
        }
    }
}
