package com.yongyonglee.vendor.domain.product.exception;

import com.yongyonglee.vendor.domain.product.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class ProductException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public ProductException(final ExceptionMessage exceptionMessage) {
        super("[Product Exception]" + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
