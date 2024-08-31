package com.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.DeviceProfile;
import com.demo.repository.DeviceProfileRepository;

import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author: Sanapala Muralidharan
 * @date: 30 Aug 2024 6:58:43 pm
 * @version:3.x
 */
@Service
public class DeviceProfileService {

    private static final Logger log = LoggerFactory.getLogger(DeviceProfileService.class);

    @Autowired
    private DeviceProfileRepository deviceProfileRepository;

    public DeviceProfile matchOrCreateDeviceProfile(HttpServletRequest request) {
	String userAgentString = request.getHeader("User-Agent");

	if (userAgentString == null || userAgentString.isEmpty()) {
	    throw new IllegalArgumentException("User-Agent header is missing or empty");
	}

	UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);

	String osName = userAgent.getOperatingSystem().getName();
	String osVersion = parseOsVersion(userAgent); // OS version is not typically extracted directly, may require
						      // additional
	// parsing
	String browserName = userAgent.getBrowser().getName();
	// Handle null browser version
	Version browserVersionObj = userAgent.getBrowserVersion();
	String browserVersion = (browserVersionObj != null) ? browserVersionObj.getVersion() : "Unknown";

	Optional<DeviceProfile> existingProfile = deviceProfileRepository
		.findByOsNameAndOsVersionAndBrowserNameAndBrowserVersion(osName, osVersion, browserName,
			browserVersion);
	log.info("Saving DeviceProfile with OS Version: {}", osVersion);
	if (existingProfile.isPresent()) {
	    DeviceProfile deviceProfile = existingProfile.get();
	    deviceProfile.setHitCount(deviceProfile.getHitCount() + 1);
	    return deviceProfileRepository.save(deviceProfile);
	} else {
	    DeviceProfile newDeviceProfile = new DeviceProfile();
	    newDeviceProfile.setOsName(osName);
	    newDeviceProfile.setOsVersion(osVersion);
	    newDeviceProfile.setBrowserName(browserName);
	    newDeviceProfile.setBrowserVersion(browserVersion);
	    return deviceProfileRepository.save(newDeviceProfile);
	}
    }

    public String parseOsVersion(UserAgent userAgent) {

	if (userAgent == null || userAgent.getOperatingSystem() == null) {
	    return "Unknown";
	}
	// Custom logic to parse OS version, e.g., from "Windows 10" extract "10"
	String userAgentString = userAgent.getOperatingSystem().getName();
	log.info("Operatring System Name: " + userAgentString);

	// Extracting "10" from "Windows 10"
	if (userAgentString.contains("Windows 10")) {
	    String[] parts = userAgentString.split(" ");
	    if (parts.length > 1) {
		String version = parts[1];
		// Handle Windows version formatting issues
		if (version.equals("10")) {
		    return "10";
		}

		return userAgentString.split(" ")[1];

	    }
	}
	/*
	 * // Extracts "10.15.7" from "Mac OS X 10_15_7" if
	 * (userAgentString.contains("Mac OS X")) { return
	 * userAgentString.split(" ")[3].replace('_', '.'); } // Example for Linux:
	 * "Linux; Android 10" if (userAgentString.contains("Android")) { return
	 * userAgentString.split(" ")[1]; // Extracts "10" from "Linux; Android 10" }
	 */
	// Fallback for unknown or unhandled cases
	return userAgentString.split(" ")[1];
    }
}
