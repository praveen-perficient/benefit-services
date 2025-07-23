package com.example.healthUpdateProducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer_health_plan")
public class CustomerHealthPlans {
    @Id
    private Integer id;
    private Integer customer_id;
    private Integer plan_id;
    private String enrollment_date;
    private String status;

}
