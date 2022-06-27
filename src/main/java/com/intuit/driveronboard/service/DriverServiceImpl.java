package com.intuit.driveronboard.service;

import com.intuit.driveronboard.dao.DriverDocumentRepository;
import com.intuit.driveronboard.dao.DriverRepository;
import com.intuit.driveronboard.dao.RideReadyDriverRepository;
import com.intuit.driveronboard.dto.DriverDocumentResponseDto;
import com.intuit.driveronboard.dto.DriverDocumentsDto;
import com.intuit.driveronboard.dto.DriverDto;
import com.intuit.driveronboard.exception.UserAlreadyExistException;
import com.intuit.driveronboard.model.Driver;
import com.intuit.driveronboard.model.DriverDocuments;
import com.intuit.driveronboard.model.DriverRideStatus;
import com.intuit.driveronboard.model.RideReadyDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DriverServiceImpl implements DriverService{
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private FileStorageService localFileStorageService;

    @Autowired
    private DriverDocumentRepository driverDocumentRepository;

    @Autowired
    private RideReadyDriverRepository rideReadyDriverRepository;


    private Logger logger = LoggerFactory.getLogger(DriverRepository.class);

    /**
     * Function to register a driver in DB
     * @param driverDto dto object representing a driver
     * @throws UserAlreadyExistException if anything fails
     */
    @Override
    public void register(DriverDto driverDto) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if(checkIfUserExist(driverDto.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        Driver driver = new Driver();
        BeanUtils.copyProperties(driverDto, driver);
        encodePassword(driver, driverDto);
        driverRepository.save(driver);
        logger.info("Driver data saved to DB");
    }

    /**
     * Check if driver exists with this email id
     * @param email string email id
     * @return true if exists, otherwise false
     */
    @Override
    public boolean checkIfUserExist(String email) {
        return driverRepository.findByEmail(email) !=null;
    }

    /**
     * Encode password from plain text
     * @param driver driver object to et encrypted password
     * @param driverDto dto object to get plain passsword
     */
    private void encodePassword( Driver driver, DriverDto driverDto){
        driver.setPassword(passwordEncoder.encode(driverDto.getPassword()));
    }

    /**
     * Function to upload all documents of a driver
     * @param documents all documents with multipart files
     * @param id driver long id
     * @return response object dto
     */
    @Override
    public DriverDocumentResponseDto uploadDriverDocuments(DriverDocumentsDto documents, Long id){
        DriverDocuments driverDocuments = new DriverDocuments();
        DriverDocuments docs = driverDocumentRepository.findByDriverId(id);
        if(docs != null && docs.getDriverLicenseFrontUri() != null){
            driverDocumentRepository.deleteById(docs.getId());
        }
        driverDocuments.setDriverLicenseBackUri(uploadOneFile(documents.getDriverLicenseBack(), id));
        driverDocuments.setDriverLicenseFrontUri(uploadOneFile(documents.getDriverLicenseFront(), id));
        driverDocuments.setUidFrontUri(uploadOneFile(documents.getUidFront(), id));
        driverDocuments.setUidBackUri(uploadOneFile(documents.getUidBack(), id));
        driverDocuments.setPanFrontUri(uploadOneFile(documents.getPanFront(), id));
        driverDocuments.setPanBackUri(uploadOneFile(documents.getPanBack(), id));
        driverDocuments.setDriverId(id);
        driverDocumentRepository.save(driverDocuments);
        DriverDocumentResponseDto responseDto = new DriverDocumentResponseDto();
        BeanUtils.copyProperties(driverDocuments, responseDto);
        return responseDto;
    }

    /**
     * Function to upload one file at a specific location
     * @param file file to upload
     * @param id driver id for name
     * @return downloadable url
     */
    public String uploadOneFile(MultipartFile file, Long id) {
        String fileName = localFileStorageService.storeFile(file, String.valueOf(id));

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(fileName)
                .toUriString();
    }

    /**
     * Download file function
     * @param fileName
     * @return
     */
    @Override
    public Resource downloadFileAsResource(String fileName){
       return localFileStorageService.loadFileAsResource(fileName);
    }

    /**
     * Function to mark driver ready for ride, will only be done if driver is marked valid and is not already enabled to drive
     * @param driverId driver long id
     * @param readyForRide boolean value if driver is ready to ride
     * @return boolean value if enabled to take ride
     */
    @Override
    public Boolean driverReadyForRide(Long driverId, Boolean readyForRide) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if(driver.isPresent()) {
            if(driver.get().isValid()){
                if(rideReadyDriverRepository.findByDriverId(driverId).isEmpty())
                    rideReadyDriverRepository.save(new RideReadyDriver(driver.get(), DriverRideStatus.ENABLED));
                return true;
            }
        }
        return false;
    }
}
