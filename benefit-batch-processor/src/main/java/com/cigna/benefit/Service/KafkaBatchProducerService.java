package com.cigna.benefit.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.cigna.benefit.model.HealthPlanBenefit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.List;

@Service
public class KafkaBatchProducerService {

    private static final String TOPIC = "health-benefits";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaBatchProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBatch() {
        try {
            InputStreamReader reader = new InputStreamReader(
                    new ClassPathResource("plans.csv").getInputStream()
            );

            CsvToBean<HealthPlanBenefit> csvToBean = new CsvToBeanBuilder<HealthPlanBenefit>(reader)
                    .withType(HealthPlanBenefit.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<HealthPlanBenefit> plans = csvToBean.parse();

            ObjectMapper mapper = new ObjectMapper();
            String jsonArray = mapper.writeValueAsString(plans);

            for (HealthPlanBenefit plan : plans) {
                kafkaTemplate.send(TOPIC, plan);
            }

            System.out.println("CSV data sent to Kafka topic: " + TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
