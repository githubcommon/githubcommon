package com.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Sanapala Muralidharan
 * @date: 30 Aug 2024 6:56:22 pm
 * @version:3.x
 */
@Entity
@Table(name = "device_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;

    private String osName;
    private String osVersion;
    private String browserName;
    private String browserVersion;
    private int hitCount = 1;
}
