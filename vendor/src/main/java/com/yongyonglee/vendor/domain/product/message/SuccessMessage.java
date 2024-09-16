package com.yongyonglee.vendor.domain.product.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_PRODUCT_SUCCESS(HttpStatus.CREATED, "상품 등록을 성공했습니다"),
    GET_PRODUCT_SUCCESS(HttpStatus.OK, "상품 조회를 성공했습니다"),
    SEARCH_PRODUCT_SUCCESS(HttpStatus.OK, "상품 검색을 성공했습니다"),
    UPDATE_PRODUCT_SUCCESS(HttpStatus.OK, "상품 수정을 성공했습니다"),
    DELETE_PRODUCT_SUCCESS(HttpStatus.OK, "상품 삭제를 성공했습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
