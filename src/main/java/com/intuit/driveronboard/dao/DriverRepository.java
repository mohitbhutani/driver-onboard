package com.intuit.driveronboard.dao;

import com.intuit.driveronboard.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {


    Driver findByEmail(String email);
}
