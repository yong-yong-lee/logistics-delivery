package com.yongyonglee.hub.domain.hub_route.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    CREATE_HUB_ROUTE_SUCCESS(HttpStatus.CREATED, "허브 이동정보 생성을 성공했습니다"),
    GET_HUB_ROUTE_SUCCESS(HttpStatus.OK, "허브 이동정보 조회를 성공했습니다"),
    SEARCH_HUB_ROUTE_SUCCESS(HttpStatus.OK, "허브 이동정보 검색을 성공했습니다"),
    UPDATE_HUB_ROUTE_SUCCESS(HttpStatus.OK, "허브 이동정보 수정을 성공했습니다"),
    DELETE_HUB_ROUTE_SUCCESS(HttpStatus.OK, "허브 이동정보 삭제를 성공했습니다");

    private final HttpStatus httpStatus;
    private final String message;
}
