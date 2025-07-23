package com.poc.mongodb.controller;

import com.poc.mongodb.model.CustomerHealthPlans;
import com.poc.mongodb.model.Customers;
import com.poc.mongodb.model.HealthPlans;
import com.poc.mongodb.service.HealthBeneService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
