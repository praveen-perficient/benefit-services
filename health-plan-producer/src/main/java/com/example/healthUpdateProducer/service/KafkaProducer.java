package com.example.healthUpdateProducer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final String TOPIC = "benefit-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendHealthPlan(JsonNode healthPlanUpdate) {
        try {
            String message = objectMapper.writeValueAsString(healthPlanUpdate);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("Produced to Kafka: " + message);
        } catch (Exception e) {
            System.err.println("Error producing message: " + e.getMessage());
        }
    }
}
