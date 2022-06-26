package com.intuit.driveronboard.utils;

import com.intuit.driveronboard.dto.ResponseObject;
import com.intuit.driveronboard.dto.ResponseStatus;

public class ResponseUtil {
    public static <T> ResponseObject<T> createSuccessResponse(T obj){
        return new ResponseObject<>(ResponseStatus.SUCCESS, obj);
    }

    public static <T> ResponseObject<T> createErrorResponse(String errorMessage, T obj){
        return new ResponseObject<>(ResponseStatus.FAILURE, errorMessage, obj);
    }
}
