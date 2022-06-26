package com.intuit.driveronboard.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MultipartValidator implements ConstraintValidator<ValidMultipartFile, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        if (multipartFile.getSize() == 0) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("File uploaded is empty")
                    .addConstraintViolation();
            return false;
        }
        String contentType = multipartFile.getContentType();
        if (!isSupportedContentType(contentType)) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("File uploaded with invalid extension, only .jpg, .png and .pdf are supported.")
                    .addConstraintViolation();
            return false;
        }


        return true;
    }

    private boolean isSupportedContentType(String contentType) {
        List<String> supportedContents = List.of("image/jpg", "image/jpeg", "image/png", "application/pdf");
        return supportedContents.contains(contentType);
    }
}
