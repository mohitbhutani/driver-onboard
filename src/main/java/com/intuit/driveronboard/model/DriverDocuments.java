package com.intuit.driveronboard.model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "driver_documents")
public class DriverDocuments  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "driver_id")
    private long driverId;
    private String driverLicenseFrontUri;
    private String driverLicenseBackUri;
    private String uidFrontUri;
    private String uidBackUri;
    private String panFrontUri;
    private String panBackUri;

    public DriverDocuments(Long id, Long driverId, String driverLicenseFrontUri, String driverLicenseBackUri, String uidFrontUri, String uidBackUri, String panFrontUri, String panBackUri) {
        this.id = id;
        this.driverId = driverId;
        this.driverLicenseFrontUri = driverLicenseFrontUri;
        this.driverLicenseBackUri = driverLicenseBackUri;
        this.uidFrontUri = uidFrontUri;
        this.uidBackUri = uidBackUri;
        this.panFrontUri = panFrontUri;
        this.panBackUri = panBackUri;
    }

    public DriverDocuments() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

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
}
