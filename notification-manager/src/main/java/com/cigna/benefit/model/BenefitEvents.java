package com.cigna.benefit.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "benefit-events")
@Data
public class BenefitEvents {

    @Id
    private String id;
    private String customerName;
    private String planId;
    private String message;
    private boolean isRead = false;
}
