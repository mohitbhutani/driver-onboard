package com.intuit.driveronboard.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * We can implement multiple ways like Local File Storage, Aws File Storage, Azure File storage etc
 */
public interface FileStorageService {
    String storeFile(MultipartFile file, String fileNamePrefix);

    Resource loadFileAsResource(String fileName);
}
