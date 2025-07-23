package com.poc.mongodb.repository;

import com.poc.mongodb.model.CustomerHealthPlans;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerHealthPlansRepository extends MongoRepository<CustomerHealthPlans,String> {
}
