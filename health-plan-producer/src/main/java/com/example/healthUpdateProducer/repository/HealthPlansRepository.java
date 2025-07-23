package com.example.healthUpdateProducer.repository;

import com.example.healthUpdateProducer.model.HealthPlans;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthPlansRepository extends MongoRepository<HealthPlans, Integer> {
}
