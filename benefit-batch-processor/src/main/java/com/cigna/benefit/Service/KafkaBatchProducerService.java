package com.cigna.benefit.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.cigna.benefit.model.HealthPlanBenefit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.util.List;

@Service
@Slf4j
public class KafkaBatchProducerService {

    private static final String TOPIC = "health-benefits";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaBatchProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBatch() {
        try {
            log.info("Starting batch send of health plan benefits from CSV...");

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
            log.debug("Parsed JSON array: {}", jsonArray);

            for (HealthPlanBenefit plan : plans) {
                kafkaTemplate.send(TOPIC, plan);
                log.debug("Sent plan to Kafka: {}", plan);
            }

            log.info("Successfully sent {} health plans to Kafka topic '{}'", plans.size(), TOPIC);

        } catch (Exception e) {
            log.error("Error while sending batch data to Kafka: {}", e.getMessage(), e);
        }
    }
}