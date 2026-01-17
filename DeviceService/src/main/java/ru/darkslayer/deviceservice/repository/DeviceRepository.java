package ru.darkslayer.deviceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darkslayer.deviceservice.entity.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
