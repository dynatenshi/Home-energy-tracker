package ru.darkslayer.ingestionservice.simulation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Component
public class ParallelDataSimulator extends AbstractDataSimulator implements CommandLineRunner {
    private final ExecutorService executorService;

    @Value("${simulation.parallel-threads}")
    private int parallelThreads;

    @Value("${simulation.requests-per-interval}")
    private int requestsPerInterval;

    public ParallelDataSimulator() {
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Parallel Data Simulator started");
        ((ThreadPoolExecutor)executorService).setCorePoolSize(parallelThreads);
    }

    @Override
    @Scheduled(fixedRateString = "${simulation.interval-ms}")
    public void sendMockData() {
        int batchSize = requestsPerInterval / parallelThreads;
        int remainder = requestsPerInterval % parallelThreads;

        for (int i = 0; i < parallelThreads; i++) {
            int requestsForThread = batchSize + (i < remainder ? 1 : 0);
            executorService.submit(() -> super.simulate(requestsForThread));
        }
    }
}
