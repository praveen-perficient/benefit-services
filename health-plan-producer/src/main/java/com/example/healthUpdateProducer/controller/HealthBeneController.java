package com.example.healthUpdateProducer.controller;

import com.example.healthUpdateProducer.model.CustomerHealthPlans;
import com.example.healthUpdateProducer.model.Customers;
import com.example.healthUpdateProducer.model.HealthPlans;
import com.example.healthUpdateProducer.service.HealthBeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class HealthBeneController
{
    @Autowired
    HealthBeneService health_service;


    @ApiIgnore
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
    @GetMapping("/customers")
    public List<Customers> getAllCustomers(){return health_service.getCustomerData();}

    @GetMapping("/health_plans")
    public List<HealthPlans> getAllHealthPlans()
    {
        return health_service.getHealthPlanData();
    }

    @GetMapping("/cust_health_plans")
    public List<CustomerHealthPlans> getAllCustHealthPlans(){return health_service.getCustHealthPlanData();}

    @GetMapping("/health_plans/{id}")
    public HealthPlans getHealthPlansById(@PathVariable Integer id){
        return health_service.getHealthPlanById(id);
    }

}

