package com.ntconsult.adapter.inbound.kafka;

import com.ntconsult.application.domain.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "reservation_notifications", groupId = "reservation-group")
    public void consume(Notification notification) {
        System.out.println("Received notification: " + notification);
    }
}
