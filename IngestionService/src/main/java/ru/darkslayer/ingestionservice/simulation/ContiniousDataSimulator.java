package ru.darkslayer.ingestionservice.simulation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContiniousDataSimulator extends AbstractDataSimulator implements CommandLineRunner {

    @Value("${simulation.requests-per-interval}")
    private int requestsPerInterval;

    @Override
    public void run(String... args) throws Exception {
        log.info("Continious Data Simulator started");
    }

    @Override
    @Scheduled(fixedRateString = "${simulation.interval-ms}")
    public void sendMockData() {
        super.simulate(requestsPerInterval);
    }
}
