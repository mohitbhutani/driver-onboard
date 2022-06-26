package com.intuit.driveronboard.model;

import javax.persistence.*;

@Entity
@Table(name = "ride_ready_driver")
public class RideReadyDriver {
    @Id
    @Column(name = "driver_id")
    private Long id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "driver_id")
    private Driver driver;
    private DriverRideStatus driverRideStatus;

    public RideReadyDriver() {
    }

    public RideReadyDriver(Driver driver, DriverRideStatus driverRideStatus) {
        this.driver = driver;
        this.driverRideStatus = driverRideStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public DriverRideStatus getDriverRideStatus() {
        return driverRideStatus;
    }

    public void setDriverRideStatus(DriverRideStatus driverRideStatus) {
        this.driverRideStatus = driverRideStatus;
    }
}
