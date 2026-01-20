package ru.darkslayer.usageservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDto {
    private Long id;
    private String name;
    private String type;
    private String location;
    private Long userId;
    private Double energyConsumed;
}