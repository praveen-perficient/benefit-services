package com.example.healthUpdateProducer.repository;

import com.example.healthUpdateProducer.model.Customers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customers, String> {
}
