package com.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.DeviceProfile;
import com.demo.repository.DeviceProfileRepository;
import com.demo.service.DeviceProfileService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author: Sanapala Muralidharan
 * @date: 30 Aug 2024 7:35:21 pm 
 * @version:3.x
 */
@RestController
@RequestMapping("/api/device")
public class DeviceProfileController {

    @Autowired
    private DeviceProfileService deviceProfileService;
    @Autowired
    private DeviceProfileRepository deviceProfileRepository;

    // Get a Device by ID
    @GetMapping("/{id}")
    public ResponseEntity<DeviceProfile> getDeviceById(@PathVariable Long id) {
	Optional<DeviceProfile> deviceProfile = deviceProfileRepository.findById(id);
	if (deviceProfile.isPresent()) {
	    return ResponseEntity.ok(deviceProfile.get());
	} else {
	    return ResponseEntity.notFound().build();
	}
    }

    // Get all Devices for a given OS name
    @GetMapping("/os/{osName}")
    public ResponseEntity<List<DeviceProfile>> getDevicesByOsName(@PathVariable String osName) {
	List<DeviceProfile> devices = deviceProfileRepository.findByOsName(osName);
	return ResponseEntity.ok(devices);
    }
    
    @PostMapping("/match")
    public DeviceProfile matchDevice(HttpServletRequest request) {
	return deviceProfileService.matchOrCreateDeviceProfile(request);
    }

    // Delete a Device by ID(s)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDeviceById(@PathVariable Long id) {
	if (deviceProfileRepository.existsById(id)) {
	    deviceProfileRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	} else {
	    return ResponseEntity.notFound().build();
	}
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDevicesByIds(@RequestParam List<Long> ids) {
	List<DeviceProfile> devicesToDelete = deviceProfileRepository.findAllById(ids);
	if (!devicesToDelete.isEmpty()) {
	    deviceProfileRepository.deleteAll(devicesToDelete);
	    return ResponseEntity.noContent().build();
	} else {
	    return ResponseEntity.notFound().build();
	}
    }

}
