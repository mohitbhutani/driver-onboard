package com.intuit.driveronboard.dto;

import java.io.Serializable;

public class ResponseObject<T> implements Serializable {

    private ResponseStatus status;
    private String errorMessage;
    private T data;

    public ResponseObject(ResponseStatus status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseObject(ResponseStatus status, String errorMessage, T data) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
