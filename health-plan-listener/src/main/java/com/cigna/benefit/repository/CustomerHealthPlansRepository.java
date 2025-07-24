package com.cigna.benefit.repository;

import com.cigna.benefit.model.CustomerHealthPlans;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerHealthPlansRepository extends MongoRepository<CustomerHealthPlans,String> {
}
