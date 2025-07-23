package com.poc.JsonFor.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HealthPlanBenefit {
    private int id;
    private String plan_name;
    private String plan_type;
    private String provider;
    private Fields fields;
    private String event_message;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Fields {
        @JsonProperty("includes_prescription")
        private boolean includesPrescription;

        @JsonProperty("dental_coverage")
        private boolean dentalCoverage;

        @JsonProperty("vision_coverage")
        private boolean visionCoverage;
    }
}
