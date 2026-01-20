package ru.darkslayer.alertservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.darkslayer.alertservice.entity.Alert;
import ru.darkslayer.alertservice.repository.AlertRepository;

import java.time.LocalDateTime;

@Service
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final AlertRepository alertRepository;

    @Autowired
    public EmailService(JavaMailSender mailSender, AlertRepository alertRepository) {
        this.mailSender = mailSender;
        this.alertRepository = alertRepository;
    }

    public void sendEmail(String to,
                          String subject,
                          String body,
                          Long userId) {
        log.info("Sending email to {}, subject: {}", to, subject);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("noreply@darkslayer.com");
        message.setSubject(subject);
        message.setText(body);

        try {
            mailSender.send(message);
            saveAlert(true, userId);

        } catch (MailException e) {
            log.error("Failed to send message to {}", to, e);

            saveAlert(false, userId);
            return;
        }
        log.info("Email sent to: {}", to);
    }

    private void saveAlert(boolean status, Long userId) {
        final Alert alert = Alert.builder()
                .sent(status)
                .createdAt(LocalDateTime.now())
                .userId(userId)
                .build();

        alertRepository.saveAndFlush(alert);
    }
}
