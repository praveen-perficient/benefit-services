package com.cigna.benefit.repository;

import com.cigna.benefit.model.Customers;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customers,String>
{

}
