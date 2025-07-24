package com.cigna.benefit.controller;

import com.cigna.benefit.model.CustomerHealthPlans;
import com.cigna.benefit.model.Customers;
import com.cigna.benefit.model.HealthPlans;
import com.cigna.benefit.service.HealthBeneService;
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
