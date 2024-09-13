package com.yongyonglee.vendor.global.response;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;
import lombok.NonNull;

@Builder(access = PRIVATE)
public record ExceptionResponse(
//        boolean success,
        @NonNull
        String message
) implements CommonResponse {

    public static ExceptionResponse of(String message) {
        return ExceptionResponse.builder()
                .message(message)
                .build();
    }
}
