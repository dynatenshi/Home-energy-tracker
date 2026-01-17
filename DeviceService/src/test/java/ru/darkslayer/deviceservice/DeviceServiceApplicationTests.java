package ru.darkslayer.deviceservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.darkslayer.deviceservice.entity.Device;
import ru.darkslayer.deviceservice.repository.DeviceRepository;
import ru.darkslayer.deviceservice.model.DeviceType;

@Slf4j
@SpringBootTest
class DeviceServiceApplicationTests {

    public static final int NUMBER_OF_DEVICES = 200;
    public static final int USER_AMOUNT = 10;
    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void createDevices() {
        for (int i = 0; i < NUMBER_OF_DEVICES; i++) {
            Device device = Device.builder()
                    .name("Device" + i)
                    .type(DeviceType.values()[i % DeviceType.values().length])
                    .location("Location " + (i%3)+1)
                    .userId((long)(i%USER_AMOUNT)+1)
                    .build();
            deviceRepository.save(device);
        }
        log.info("Updated device repository");
    }
}
