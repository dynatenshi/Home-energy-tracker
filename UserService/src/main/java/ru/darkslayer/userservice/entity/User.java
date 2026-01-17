package ru.darkslayer.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String surname;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column
    private String address;

    @Column(nullable = false)
    private boolean alerting;

    @Column(name = "energy_alerting_threshold", nullable = false)
    private double energyAlertingThreshold;
}
