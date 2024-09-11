package com.yongyonglee.hub.domain.hub.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_HUB_SUCCESS(HttpStatus.CREATED, "허브(물류센터) 생성을 성공했습니다"),
    GET_HUB_SUCCESS(HttpStatus.OK, "허브(물류센터) 조회를 성공했습니다"),
    SEARCH_HUB_SUCCESS(HttpStatus.OK, "허브(물류센터) 검색을 성공했습니다"),
    DELETE_HUB_SUCCESS(HttpStatus.OK, "허브(물류센터) 삭제를 성공했습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
