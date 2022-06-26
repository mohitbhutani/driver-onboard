package com.intuit.driveronboard.service;

import com.intuit.driveronboard.dto.DriverDocumentResponseDto;
import com.intuit.driveronboard.dto.DriverDocumentsDto;
import com.intuit.driveronboard.dto.DriverDto;
import com.intuit.driveronboard.exception.UserAlreadyExistException;
import org.springframework.core.io.Resource;

public interface DriverService {

    void register(DriverDto user) throws UserAlreadyExistException;
    boolean checkIfUserExist(String email);
    DriverDocumentResponseDto uploadDriverDocuments(DriverDocumentsDto documents, Long id);

    Resource downloadFileAsResource(String fileName);

    Boolean driverReadyForRide(Long driverId, Boolean readyForRide);
}
