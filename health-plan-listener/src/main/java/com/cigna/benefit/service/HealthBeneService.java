package com.cigna.benefit.service;

import com.cigna.benefit.model.CustomerHealthPlans;
import com.cigna.benefit.model.Customers;
import com.cigna.benefit.model.HealthPlans;
import com.cigna.benefit.repository.CustomerHealthPlansRepository;
import com.cigna.benefit.repository.CustomerRepository;
import com.cigna.benefit.repository.HealthPlansRepository;
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
