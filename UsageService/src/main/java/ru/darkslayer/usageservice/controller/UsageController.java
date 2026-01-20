package ru.darkslayer.usageservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.darkslayer.usageservice.dto.UsageDto;
import ru.darkslayer.usageservice.service.UsageService;

@RestController
@RequestMapping("/api/v1/usage")
public class UsageController {
    private final UsageService usageService;

    @Autowired
    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UsageDto> getUserDeviceUsage(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "3") int days) {
        UsageDto usageDto = usageService.getXDaysUsageForUser(userId, days);
        return ResponseEntity.ok(usageDto);
    }
}
