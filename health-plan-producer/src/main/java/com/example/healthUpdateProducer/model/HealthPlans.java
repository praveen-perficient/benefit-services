package com.example.healthUpdateProducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "health_plans")
public class HealthPlans {
    @Id
    private Integer id;
    private String plan_name;
    private String plan_type;
    private String provider;
    private String coverage_area;
    private Double monthly_premium;
    private Double deductible;
    private String coinsurance;
    private Double copay_primary;
    private Double copay_specialist;
    private Double out_of_pocket_max;
    private String network_type;
    private Boolean referral_required;
    private Boolean includes_prescription;
    private Boolean dental_coverage;
    private Boolean vision_coverage;
    private String plan_url;
    private String plan_notes;
    private String effective_date;
}

