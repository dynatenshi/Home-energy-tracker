package ru.darkslayer.ingestionservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.darkslayer.ingestionservice.dto.EnergyUsageDto;
import ru.darkslayer.kafka.event.EnergyUsageEvent;

@Slf4j
@Service
public class IngestionService {

    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    @Autowired
    public IngestionService(KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void ingestEnergyUsage(EnergyUsageDto request) {
        EnergyUsageEvent event = EnergyUsageEvent.builder()
                .deviceId(request.deviceId())
                .energyConsumed(request.energyConsumed())
                .timestamp(request.timestamp())
                .build();
        kafkaTemplate.send("energy-usage", event);
        log.info("Ingested Energy Usage Event: {}", event);
    }
}
