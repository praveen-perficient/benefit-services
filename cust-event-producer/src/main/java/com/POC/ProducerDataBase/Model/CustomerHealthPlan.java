package com.POC.ProducerDataBase.Model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer_health_plan")
public class CustomerHealthPlan {

    @Id
    private Integer id;
    private Integer customer_id;
    private Integer plan_id;
    private LocalDate enrollment_date;
    private String status;
}


