package com.example.healthUpdateProducer.controller;

import com.example.healthUpdateProducer.service.KafkaProducer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/healthplans")
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public String publishHealthPlan(@RequestBody JsonNode healthPlanUpdate) {
        kafkaProducer.sendHealthPlan(healthPlanUpdate);
        return "Health plan update sent to Kafka successfully!";
    }
}
