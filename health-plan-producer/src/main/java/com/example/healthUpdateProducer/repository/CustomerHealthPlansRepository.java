package com.example.healthUpdateProducer.repository;

import com.example.healthUpdateProducer.model.CustomerHealthPlans;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerHealthPlansRepository extends MongoRepository<CustomerHealthPlans, String> {
}
