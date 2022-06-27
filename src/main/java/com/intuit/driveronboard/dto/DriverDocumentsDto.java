package com.intuit.driveronboard.dto;

import com.intuit.driveronboard.utils.ValidMultipartFile;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * Request object to upload files
 */
public class DriverDocumentsDto implements Serializable {
    @Valid
    private MultipartFile driverLicenseFront;
    @Valid
    private MultipartFile driverLicenseBack;
    @Valid
    private MultipartFile uidFront;
    @Valid
    private MultipartFile uidBack;
    @Valid
    private MultipartFile panFront;
    @Valid
    private MultipartFile panBack;

    public MultipartFile getDriverLicenseFront() {
        return driverLicenseFront;
    }

    public void setDriverLicenseFront(MultipartFile driverLicenseFront) {
        this.driverLicenseFront = driverLicenseFront;
    }

    public MultipartFile getDriverLicenseBack() {
        return driverLicenseBack;
    }

    public void setDriverLicenseBack(MultipartFile driverLicenseBack) {
        this.driverLicenseBack = driverLicenseBack;
    }

    public MultipartFile getUidFront() {
        return uidFront;
    }

    public void setUidFront(MultipartFile uidFront) {
        this.uidFront = uidFront;
    }

    public MultipartFile getUidBack() {
        return uidBack;
    }

    public void setUidBack(MultipartFile uidBack) {
        this.uidBack = uidBack;
    }

    public MultipartFile getPanFront() {
        return panFront;
    }

    public void setPanFront(MultipartFile panFront) {
        this.panFront = panFront;
    }

    public MultipartFile getPanBack() {
        return panBack;
    }

    public void setPanBack(MultipartFile panBack) {
        this.panBack = panBack;
    }
}
