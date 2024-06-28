package com.ntconsult.application.usecase;

import com.ntconsult.application.domain.Notification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationUseCase {
    private static final String TOPIC = "reservation_notifications";

    private final KafkaTemplate<String, Notification> kafkaTemplate;

    public NotificationUseCase(KafkaTemplate<String, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(Notification notification) {
        kafkaTemplate.send(TOPIC, notification);
    }
}
