package com.yongyonglee.hub.domain.hub.exception;

import com.yongyonglee.hub.domain.hub.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class HubException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public HubException(ExceptionMessage exceptionMessage) {
        super("[Hub Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
