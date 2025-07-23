package com.poc.mongodb.repository;

import com.poc.mongodb.model.HealthPlans;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthPlansRepository extends MongoRepository<HealthPlans,String> {
}
