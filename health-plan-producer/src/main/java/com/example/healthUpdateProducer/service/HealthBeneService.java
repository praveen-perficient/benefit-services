package com.example.healthUpdateProducer.service;

import com.example.healthUpdateProducer.model.CustomerHealthPlans;
import com.example.healthUpdateProducer.model.Customers;
import com.example.healthUpdateProducer.model.HealthPlans;
import com.example.healthUpdateProducer.repository.CustomerHealthPlansRepository;
import com.example.healthUpdateProducer.repository.CustomerRepository;
import com.example.healthUpdateProducer.repository.HealthPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthBeneService {
    @Autowired
    CustomerRepository cust_repo;

    @Autowired
    CustomerHealthPlansRepository cust_health_repo;

    @Autowired
    HealthPlansRepository health_plan_repo;

    public List<Customers> getCustomerData() {
        return cust_repo.findAll();
    }

    public List<HealthPlans> getHealthPlanData() {
        return health_plan_repo.findAll();
    }

    public List<CustomerHealthPlans> getCustHealthPlanData() {
        return cust_health_repo.findAll();
    }

    public HealthPlans getHealthPlanById(Integer id){
        return health_plan_repo.findById(id).orElse(null);
    }

}