package ru.darkslayer.deviceservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.darkslayer.model.DeviceType;

@Entity
@Table(name = "devices")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private DeviceType type;

    @Column
    private String location;

    @Column(name = "user_id")
    private Long userId;
}
