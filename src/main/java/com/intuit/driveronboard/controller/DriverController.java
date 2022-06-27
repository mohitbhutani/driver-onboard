package com.intuit.driveronboard.controller;

import com.intuit.driveronboard.dto.*;
import com.intuit.driveronboard.exception.FileStorageException;
import com.intuit.driveronboard.exception.UserAlreadyExistException;
import com.intuit.driveronboard.model.CurrentUser;
import com.intuit.driveronboard.service.DriverService;
import com.intuit.driveronboard.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

@Controller("driver")
@Validated
public class DriverController {

    @Autowired
    private DriverService driverService;
    private Logger logger = LoggerFactory.getLogger(DriverController.class);

    /**
     * Function to register driver using email and password, if email already exists throw an exception
     * @param driverDto driver information
     * @param bindingResult anything that fails during binding dto
     * @return Response entity with success of failure response
     */
    @PostMapping("/api/register")
    public ResponseEntity<ResponseObject<?>> driverRegistration(final @Valid @RequestBody DriverDto driverDto, final BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer("");
            for (FieldError f : bindingResult.getFieldErrors()) {
                sb.append(f.getField()).append(": ").append(f.getDefaultMessage()).append("; ");
            }
            return new ResponseEntity<>(ResponseUtil.createErrorResponse(sb.toString(), null), HttpStatus.BAD_REQUEST);

        }
        try {
            driverService.register(driverDto);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>(ResponseUtil.createErrorResponse("An account already exists for this email.", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseUtil.createErrorResponse("An error occurred while processing your request. " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(null), HttpStatus.OK);
    }

    /**
     * API to upload driver related documents
     * @param documents all documents presented by driver
     * @param authentication authentication object to get driver id logged in
     * @param bindingResult binding results if anything fails
     * @return Response Entity with download urls of all files
     */
    @PostMapping("/api/uploadDocuments")
    public ResponseEntity<ResponseObject<?>> uploadDocuments(@ModelAttribute @Valid DriverDocumentsDto documents, Authentication authentication, final BindingResult bindingResult) {
        DriverDocumentResponseDto responseDto = null;
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer("");
            for (FieldError f : bindingResult.getFieldErrors()) {
                sb.append(f.getField()).append(": ").append(f.getDefaultMessage()).append("; ");
            }
            return new ResponseEntity<>(ResponseUtil.createErrorResponse(sb.toString(), null), HttpStatus.BAD_REQUEST);

        }
        try {
            responseDto = driverService.uploadDriverDocuments(documents, ((CurrentUser) (authentication.getPrincipal())).getId());
        } catch (FileStorageException e) {
            return new ResponseEntity<>(ResponseUtil.createErrorResponse("An error occurred while storing uploaded file. Please try again. " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseUtil.createErrorResponse("An error occurred while uploading files. " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(responseDto), HttpStatus.OK);
    }

    /**
     * API to download the file uploaded by driver
     * @param fileName name of the file to be downloaded
     * @param request http request
     * @return Response Entity with the File Resource
     */
    @GetMapping("/api/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = driverService.downloadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * API to be used by Driver to mark that they are ready to take rides
     * @param readyForRideDto boolean value to mark as ready
     * @param authentication auth object
     * @return response entity with success or failure message
     */
    @PutMapping("/api/readyForRide")
    public ResponseEntity<ResponseObject<?>> readyForRide(@RequestBody ReadyForRideDto readyForRideDto, Authentication authentication) {
        Boolean value = false;
        try {
            value = driverService.driverReadyForRide(((CurrentUser) (authentication.getPrincipal())).getId(), readyForRideDto.getReadyForRide());
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseUtil.createErrorResponse("An error occurred while processing the request. " + e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ResponseObject<String> resp = value ? ResponseUtil.createSuccessResponse("Driver is validated and ready for the ride") : ResponseUtil.createErrorResponse("Driver is not validated yet hence not ready for taking rides",
                "Driver is not validated yet hence not ready for taking rides");
        return ResponseEntity.ok(resp);
    }


}
