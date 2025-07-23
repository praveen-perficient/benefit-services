package com.poc.mongodb.repository;

import com.poc.mongodb.model.Customers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customers,String>
{

}
