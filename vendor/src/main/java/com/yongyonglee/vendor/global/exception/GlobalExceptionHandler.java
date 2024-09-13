package com.yongyonglee.vendor.global.exception;

import static com.yongyonglee.vendor.global.response.ExceptionResponse.of;

import com.yongyonglee.vendor.domain.vendor.exception.VendorException;
import com.yongyonglee.vendor.global.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VendorException.class)
    public ResponseEntity<ExceptionResponse> handleVendorException(VendorException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(of(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(of(e.getLocalizedMessage()));
    }
}
