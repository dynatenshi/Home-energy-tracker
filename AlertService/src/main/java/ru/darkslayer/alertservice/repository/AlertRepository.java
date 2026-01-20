package ru.darkslayer.alertservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darkslayer.alertservice.entity.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
}
