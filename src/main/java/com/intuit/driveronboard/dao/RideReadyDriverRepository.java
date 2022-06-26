package com.intuit.driveronboard.dao;

import com.intuit.driveronboard.model.RideReadyDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RideReadyDriverRepository  extends JpaRepository<RideReadyDriver, Long> {
    Optional<RideReadyDriver> findByDriverId(Long driverId);
}
