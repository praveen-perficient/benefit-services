package com.POC.ProducerDataBase.Model;




import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "benefit-events")
public class BenifitEvents {

    @Id
    private String mongoId;  // Maps to MongoDB's "_id" field (ObjectId)

    private int id;
    private String planName;
    private String planType;
    private String provider;
    private Fields fields;
    private String eventMessage;

    @Data
    public static class Fields {
        private boolean includesPrescription;
        private boolean dentalCoverage;
        private boolean visionCoverage;
    }
}

