package com.intuit.driveronboard.service;

import com.intuit.driveronboard.dao.DriverRepository;
import com.intuit.driveronboard.dao.RideReadyDriverRepository;
import com.intuit.driveronboard.dto.DriverDto;
import com.intuit.driveronboard.exception.UserAlreadyExistException;
import com.intuit.driveronboard.model.Driver;
import com.intuit.driveronboard.model.DriverRideStatus;
import com.intuit.driveronboard.model.RideReadyDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class DriverServiceTest {
    @Mock
    DriverRepository driverRepository;

    @Mock
    RideReadyDriverRepository rideReadyDriverRepository;

    @InjectMocks
    @Spy
    DriverServiceImpl driverService;

    @Test()
    public void register_emailIdNotPresentInDB() throws UserAlreadyExistException {
        DriverDto driverDto = new DriverDto("First Name", "Last Name", "mohit@gmail.com", "strongPassword");


        Exception exception = assertThrows(UserAlreadyExistException.class, () -> {
            when(driverRepository.findByEmail(ArgumentMatchers.anyString())).thenReturn(getDriver(true));
            driverService.register(driverDto);
        });

        String expectedMessage = "User already exists for this email";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void driverReadyForRide_notValidDriver(){
        Long driverId = 1L; Boolean readyForRide = true;
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(getDriver(false)));
        Boolean result = driverService.driverReadyForRide(driverId, readyForRide);
        assertFalse(result);
    }

    @Test
    public void driverReadyForRide_ValidDriver(){
        Long driverId = 1L; Boolean readyForRide = true;
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(getDriver(true)));
        when(rideReadyDriverRepository.findByDriverId(driverId)).thenReturn(Optional.ofNullable(null));
        Boolean result = driverService.driverReadyForRide(driverId, readyForRide);
        assertTrue(result);
    }

    @Test
    public void driverReadyForRide_ValidDriverAlreadyDriveReady(){
        Long driverId = 1L; Boolean readyForRide = true;
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(getDriver(true)));
        when(rideReadyDriverRepository.findByDriverId(driverId)).thenReturn(Optional.of(new RideReadyDriver(getDriver(true), DriverRideStatus.ENABLED)));
        Boolean result = driverService.driverReadyForRide(driverId, readyForRide);
        assertTrue(result);
    }

    public Driver getDriver(Boolean isValid){
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setEmail("mohit@gmail.com");
        driver.setPassword("strongPassword");
        driver.setFirstName("Mohit");
        driver.setLastName("Bhutani");
        driver.setValid(isValid);
        return driver;
    }


}
