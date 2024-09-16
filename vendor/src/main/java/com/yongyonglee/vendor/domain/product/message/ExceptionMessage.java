package com.yongyonglee.vendor.domain.product.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 상품를 찾을 수 없습니다."),
    PRODUCT_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "상품에 대한 접근 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
