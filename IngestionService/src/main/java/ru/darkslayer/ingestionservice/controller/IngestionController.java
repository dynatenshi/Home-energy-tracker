package ru.darkslayer.ingestionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.darkslayer.ingestionservice.dto.EnergyUsageDto;
import ru.darkslayer.ingestionservice.service.IngestionService;

@RestController
@RequestMapping("/api/v1/ingestion")
public class IngestionController {

    private final IngestionService ingestionService;

    @Autowired
    public IngestionController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDto request) {
        ingestionService.ingestEnergyUsage(request);
    }
}
