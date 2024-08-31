package com.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.entity.DeviceProfile;
import com.demo.repository.DeviceProfileRepository;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author: Sanapala Muralidharan
 * @date: 30 Aug 2024 7:14:49 pm 
 * @version:3.x
 */
@ExtendWith(MockitoExtension.class)
public class DeviceProfileServiceTest {

    @Mock
    private DeviceProfileRepository deviceProfileRepository;

    @InjectMocks
    private DeviceProfileService deviceProfileService;

    @Mock
    private HttpServletRequest httpServletRequest;

    private DeviceProfile existingDeviceProfile;

    @BeforeEach
    void setUp() {
        existingDeviceProfile = new DeviceProfile();
        existingDeviceProfile.setDeviceId(1L);
        existingDeviceProfile.setOsName("Windows");
        existingDeviceProfile.setOsVersion("10");
        existingDeviceProfile.setBrowserName("Chrome");
        existingDeviceProfile.setBrowserVersion("89.0");
        existingDeviceProfile.setHitCount(1);
    }
    
    @Test
    void testMatchOrCreateDeviceProfile_NewDevice() {
        when(httpServletRequest.getHeader("User-Agent")).thenReturn("Mozilla/5.0 (Windows 10; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        when(deviceProfileRepository.findByOsNameAndOsVersionAndBrowserNameAndBrowserVersion(anyString(), anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(deviceProfileRepository.save(any(DeviceProfile.class))).thenReturn(existingDeviceProfile);

        DeviceProfile result = deviceProfileService.matchOrCreateDeviceProfile(httpServletRequest);

        assertNotNull(result);
        assertEquals("Windows 10", result.getOsName());
        assertEquals("10", result.getOsVersion());
        assertEquals("Chrome", result.getBrowserName());
        assertEquals("89.0", result.getBrowserVersion());
        assertEquals(1, result.getHitCount());

        verify(deviceProfileRepository, times(1)).save(any(DeviceProfile.class));
    }
    
    @Test
    void testMatchOrCreateDeviceProfile_ExistingDevice() {
        when(httpServletRequest.getHeader("User-Agent")).thenReturn("Mozilla/5.0 (Windows 10; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36");
        when(deviceProfileRepository.findByOsNameAndOsVersionAndBrowserNameAndBrowserVersion(anyString(), anyString(), anyString(), anyString())).thenReturn(Optional.of(existingDeviceProfile));

        DeviceProfile result = deviceProfileService.matchOrCreateDeviceProfile(httpServletRequest);

        assertNotNull(result);
        assertEquals(2, result.getHitCount());

        verify(deviceProfileRepository, times(1)).save(existingDeviceProfile);
    }
    
    //Testing OS Version Parsing
    
    @Test
    void testParseOsVersion_Windows() {
        String userAgentString = "Mozilla/5.0 (Windows 10; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36";
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);

        String osVersion = deviceProfileService.parseOsVersion(userAgent);

        assertEquals("10", osVersion);
    }

    /*
     * @Test void testParseOsVersion_MacOS() { String userAgentString =
     * "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.3 Safari/605.1.15"
     * ; UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
     * 
     * String osVersion = deviceProfileService.parseOsVersion(userAgent);
     * 
     * assertEquals("10_15_7", osVersion); }
     */

    /*
     * @Test void testParseOsVersion_UnknownOS() { String userAgentString =
     * "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.96 Safari/537.36"
     * ; UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
     * 
     * String osVersion = deviceProfileService.parseOsVersion(userAgent);
     * 
     * assertEquals("", osVersion); }
     */
    
    /*
     * @Test void testMatchOrCreateDeviceProfile_NullBrowserVersion() { String
     * userAgentString =
     * "Mozilla/5.0 (Windows NT 10; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/Unknown Safari/537.36"
     * ;
     * when(httpServletRequest.getHeader("User-Agent")).thenReturn(userAgentString);
     * 
     * DeviceProfile result =
     * deviceProfileService.matchOrCreateDeviceProfile(httpServletRequest);
     * 
     * assertNotNull(result); assertEquals("Unknown", result.getBrowserVersion()); }
     */
}
