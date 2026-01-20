package ru.darkslayer.alertservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.darkslayer.kafka.event.AlertingEvent;

@Service
@Slf4j
public class AlertService {

    private final EmailService emailService;

    @Autowired
    public AlertService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "energy-alerts", groupId = "alert-service")
    public void energyUsageAlertEvent(AlertingEvent event) {
        log.info("Received alert event: {}", event);

        final String subject = "Energy Usage Alert for User " + event.getUserId();
        final String message = "Alert: " + event.getMessage() +
                "\nThreshold: " + event.getThreshold() +
                "\nEnergy Consumed: " + event.getEnergyConsumption();
        emailService.sendEmail(event.getEmail(), subject,  message, event.getUserId());
    }
}
