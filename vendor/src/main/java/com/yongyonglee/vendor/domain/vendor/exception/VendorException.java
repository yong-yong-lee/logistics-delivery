package com.yongyonglee.vendor.domain.vendor.exception;

import com.yongyonglee.vendor.domain.vendor.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class VendorException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public VendorException(ExceptionMessage exceptionMessage) {
        super("[Vendor Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
