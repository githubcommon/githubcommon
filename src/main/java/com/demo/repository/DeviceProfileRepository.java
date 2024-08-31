package com.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.DeviceProfile;

/**
 * @author: Sanapala Muralidharan
 * @date: 30 Aug 2024 6:58:13 pm
 * @version:3.x
 */
public interface DeviceProfileRepository extends JpaRepository<DeviceProfile, Long> {

    Optional<DeviceProfile> findByOsNameAndOsVersionAndBrowserNameAndBrowserVersion(String osName, String osVersion,
	    String browserName, String browserVersion);

    Optional<DeviceProfile> findById(Long id);

    List<DeviceProfile> findByOsName(String osName);

    List<DeviceProfile> findAllById(Iterable<Long> ids);
}
