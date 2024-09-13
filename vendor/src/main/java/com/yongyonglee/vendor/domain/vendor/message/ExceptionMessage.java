package com.yongyonglee.vendor.domain.vendor.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    VENDOR_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 업체를 찾을 수 없습니다."),
    VENDOR_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "업체에 대한 접근 권한이 없습니다."),

    VENDOR_CATEGORY_INVALID(HttpStatus.NOT_FOUND, "올바르지 않은 업체 타입입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
