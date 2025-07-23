package com.consumer.benefit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class CustomerEvent {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("plan_name")
    private String planName;

    @JsonProperty("plan_type")
    private String planType;

    private String provider;

    @JsonProperty("event_message")
    private String eventMessage;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("created_time")
    private Instant createdTime;
}