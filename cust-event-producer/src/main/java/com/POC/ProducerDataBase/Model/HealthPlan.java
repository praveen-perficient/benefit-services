package com.POC.ProducerDataBase.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("health_plans")
public class HealthPlan {
    @Id
    private Integer id;
    private String plan_name;
    private String plan_type;
    private String provider;
    private String coverage_area;
    private int monthly_premium;
    private int deductible;
    private String coinsurance;
    private int copay_primary;
    private int copay_specialist;
    private int out_of_pocket_max;
    private String network_type;
    private boolean referral_required;
    private boolean includes_prescription;
    private boolean dental_coverage;
    private boolean vision_coverage;
    private String plan_url;
    private String plan_notes;
    private String effective_date;
    private Integer customer_id;
}



