package com.intuit.driveronboard.dto;

import java.io.Serializable;

/**
 * DTO object for driver to mark ready for rides
 */
public class ReadyForRideDto implements Serializable {
    private Boolean readyForRide;

    public Boolean getReadyForRide() {
        return readyForRide;
    }

    public void setReadyForRide(Boolean readyForRide) {
        this.readyForRide = readyForRide;
    }

    public ReadyForRideDto(Boolean readyForRide) {
        this.readyForRide = readyForRide;
    }

    public ReadyForRideDto() {
    }
}
