package com.yongyonglee.hub.global.exception;

import static com.yongyonglee.hub.global.response.ExceptionResponse.of;

import com.yongyonglee.hub.global.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // 유효성 검사 오류 메시지를 추출하여 첫 번째 오류 메시지 반환
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Validation failed";

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(of(errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(of(e.getLocalizedMessage()));
    }
}
