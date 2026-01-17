package ru.darkslayer.deviceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.darkslayer.deviceservice.dto.DeviceDto;
import ru.darkslayer.deviceservice.entity.Device;
import ru.darkslayer.deviceservice.exceptions.DeviceNotFoundException;
import ru.darkslayer.deviceservice.repository.DeviceRepository;

@Service
@Transactional
public class DeviceService {
    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceDto getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
        return toDto(device);
    }

    public DeviceDto createDevice(DeviceDto request) {
        Device device = Device.builder()
                .name(request.getName())
                .type(request.getType())
                .location(request.getLocation())
                .userId(request.getUserId())
                .build();
        return toDto(deviceRepository.save(device));
    }

    public DeviceDto updateDevice(Long id, DeviceDto request) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
        device.setName(request.getName());
        device.setType(request.getType());
        device.setLocation(request.getLocation());
        device.setUserId(request.getUserId());

        return toDto(deviceRepository.save(device));
    }

    public void deleteDevice(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new DeviceNotFoundException(id);
        }
        deviceRepository.deleteById(id);
    }

    private DeviceDto toDto(Device device) {
        return DeviceDto.builder()
                .id(device.getId())
                .name(device.getName())
                .type(device.getType())
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }
}
