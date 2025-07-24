package com.cigna.benefit.repository;

import com.cigna.benefit.model.HealthPlans;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HealthPlansRepository extends MongoRepository<HealthPlans,String> {
}
