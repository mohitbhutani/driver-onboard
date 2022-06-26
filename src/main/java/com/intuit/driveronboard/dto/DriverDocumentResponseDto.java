package com.intuit.driveronboard.dto;

import java.io.Serializable;

public class DriverDocumentResponseDto implements Serializable {
    private String driverLicenseFrontUri;
    private String driverLicenseBackUri;
    private String uidFrontUri;
    private String uidBackUri;
    private String panFrontUri;
    private String panBackUri;

    public String getDriverLicenseFrontUri() {
        return driverLicenseFrontUri;
    }

    public void setDriverLicenseFrontUri(String driverLicenseFrontUri) {
        this.driverLicenseFrontUri = driverLicenseFrontUri;
    }

    public String getDriverLicenseBackUri() {
        return driverLicenseBackUri;
    }

    public void setDriverLicenseBackUri(String driverLicenseBackUri) {
        this.driverLicenseBackUri = driverLicenseBackUri;
    }

    public String getUidFrontUri() {
        return uidFrontUri;
    }

    public void setUidFrontUri(String uidFrontUri) {
        this.uidFrontUri = uidFrontUri;
    }

    public String getUidBackUri() {
        return uidBackUri;
    }

    public void setUidBackUri(String uidBackUri) {
        this.uidBackUri = uidBackUri;
    }

    public String getPanFrontUri() {
        return panFrontUri;
    }

    public void setPanFrontUri(String panFrontUri) {
        this.panFrontUri = panFrontUri;
    }

    public String getPanBackUri() {
        return panBackUri;
    }

    public void setPanBackUri(String panBackUri) {
        this.panBackUri = panBackUri;
    }

    public DriverDocumentResponseDto() {
    }

    public DriverDocumentResponseDto(String driverLicenseFrontUri, String driverLicenseBackUri, String uidFrontUri, String uidBackUri, String panFrontUri, String panBackUri) {
        this.driverLicenseFrontUri = driverLicenseFrontUri;
        this.driverLicenseBackUri = driverLicenseBackUri;
        this.uidFrontUri = uidFrontUri;
        this.uidBackUri = uidBackUri;
        this.panFrontUri = panFrontUri;
        this.panBackUri = panBackUri;
    }
}
