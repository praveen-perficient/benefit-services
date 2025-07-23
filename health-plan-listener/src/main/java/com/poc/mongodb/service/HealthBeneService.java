package com.poc.mongodb.service;

import com.poc.mongodb.model.CustomerHealthPlans;
import com.poc.mongodb.model.Customers;
import com.poc.mongodb.model.HealthPlans;
import com.poc.mongodb.repository.CustomerHealthPlansRepository;
import com.poc.mongodb.repository.CustomerRepository;
import com.poc.mongodb.repository.HealthPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthBeneService {
    @Autowired
    CustomerRepository cust_repo;

    @Autowired
    CustomerHealthPlansRepository cust_health_repo;

    @Autowired
    HealthPlansRepository health_plan_repo;

    public List<Customers> getCustomerData(){
        return cust_repo.findAll();
    }
    public List<HealthPlans> getHealthPlanData(){
        return health_plan_repo.findAll();
    }
    public List<CustomerHealthPlans> getCustHealthPlanData(){
        return cust_health_repo.findAll();
    }
}
