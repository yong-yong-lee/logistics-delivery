package com.yongyonglee.vendor.domain.vendor.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_VENDOR_SUCCESS(HttpStatus.CREATED, "업체 생성을 성공했습니다"),
    GET_VENDOR_SUCCESS(HttpStatus.OK, "업체 조회를 성공했습니다"),
    SEARCH_VENDOR_SUCCESS(HttpStatus.OK, "업체 검색을 성공했습니다"),
    UPDATE_VENDOR_SUCCESS(HttpStatus.OK, "업체 수정을 성공했습니다"),
    DELETE_VENDOR_SUCCESS(HttpStatus.OK, "업체 삭제를 성공했습니다");

    private final HttpStatus httpStatus;
    private final String message;

}
