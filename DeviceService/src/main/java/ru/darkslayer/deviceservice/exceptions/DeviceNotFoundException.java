package ru.darkslayer.deviceservice.exceptions;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(String message) {
        super(message);
    }

    public DeviceNotFoundException(Long id) {
        super(String.format("Device with id '%d' not found!", id));
    }
}
