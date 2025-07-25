package com.cigna.benefit.consumer;

import com.cigna.benefit.model.BenefitEvents;
import com.cigna.benefit.model.CustomerEvent;
import com.cigna.benefit.repository.BenefitEventsRepository;
import com.cigna.benefit.websocket.WebSocketSessionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerEventListener {

    @Autowired
    private WebSocketSessionManager webSocketHandler;

    @Autowired
    private BenefitEventsRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "customer-event-topic", groupId = "health-bene-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(CustomerEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);

            // Store notification with isRead = false
            BenefitEvents notification = new BenefitEvents();
            notification.setCustomerName((String) event.getCustomerNotification().get("customerName"));
            notification.setPlanId((String) event.getCustomerNotification().get("planId"));
            notification.setMessage((String) event.getCustomerNotification().get("event_message")); // original message
            notification.setRead(false);

            repository.save(notification);
            System.out.println(json);

            // Send to WebSocket
            webSocketHandler.sendMessageToAll(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

