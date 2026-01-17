package ru.darkslayer.deviceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.darkslayer.model.DeviceType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DeviceDto {
    private Long id;
    private String name;
    private DeviceType type;
    private String location;
    private Long userId;
}
