package ru.darkslayer.ingestionservice.simulation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import ru.darkslayer.ingestionservice.dto.EnergyUsageDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

@Slf4j
public abstract class AbstractDataSimulator {
    protected Random random = new Random();
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${simulation.endpoint}")
    private String ingestionEndpoint;

    protected abstract void sendMockData();

    protected void simulate(int requestAmount) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (int i = 0; i < requestAmount; i++) {
            EnergyUsageDto dto = EnergyUsageDto.builder()
                    .deviceId(random.nextLong(1, 6))
                    .energyConsumed(Math.round(random.nextDouble(0f, 2f) * 100f) / 100f)
                    .timestamp(LocalDateTime.now()
                            .atZone(ZoneId.systemDefault()).toInstant())
                    .build();
            try {
                HttpEntity<EnergyUsageDto> request = new HttpEntity<>(dto, headers);
                restTemplate.postForEntity(ingestionEndpoint, request, Void.class);
                log.info("Sent mock data: {}", dto);
            } catch (Exception e) {
                log.error("Failed to send data: {}", e.getMessage());
            }
        }
    }
}
