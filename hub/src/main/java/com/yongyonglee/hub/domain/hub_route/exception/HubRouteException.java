package com.yongyonglee.hub.domain.hub_route.exception;

import com.yongyonglee.hub.domain.hub.message.ExceptionMessage;
import org.springframework.http.HttpStatus;

public class HubRouteException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public HubRouteException(ExceptionMessage exceptionMessage) {
        super("[HubRoute Exception] : " + exceptionMessage.getMessage());
        this.exceptionMessage = exceptionMessage;
    }

    public HttpStatus getHttpStatus() {
        return exceptionMessage.getHttpStatus();
    }

    public String getMessage() {
        return exceptionMessage.getMessage();
    }
}
