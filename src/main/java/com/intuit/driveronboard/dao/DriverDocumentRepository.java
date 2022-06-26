package com.intuit.driveronboard.dao;

import com.intuit.driveronboard.model.DriverDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverDocumentRepository extends JpaRepository<DriverDocuments, Long> {
    DriverDocuments findByDriverId(Long driverId);
}
