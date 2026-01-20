package ru.darkslayer.insightservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.darkslayer.insightservice.dto.InsightDto;
import ru.darkslayer.insightservice.service.InsightService;

@RestController
@RequestMapping("/api/v1/insight")
public class InsightController {
    private final InsightService insightService;

    @Autowired
    public InsightController(InsightService insightService) {
        this.insightService = insightService;
    }

    @GetMapping("/saving-tips/{userId}")
    public ResponseEntity<InsightDto> getSavingTips(@PathVariable Long userId) {
        final InsightDto insightDto = insightService.getSavingTips(userId);
        return ResponseEntity.ok(insightDto);
    }

    @GetMapping("/overview/{userId}")
    public ResponseEntity<InsightDto> getOverview(@PathVariable Long userId) {
        final InsightDto insightDto = insightService.getOverview(userId);
        return ResponseEntity.ok(insightDto);
    }
}
