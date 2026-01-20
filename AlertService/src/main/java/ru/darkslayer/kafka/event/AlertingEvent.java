package ru.darkslayer.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertingEvent {
    private Long userId;
    private String message;
    private double threshold;
    private double energyConsumption;
    private String email;
}
