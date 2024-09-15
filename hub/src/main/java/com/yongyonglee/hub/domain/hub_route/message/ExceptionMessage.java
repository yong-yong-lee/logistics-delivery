package com.yongyonglee.hub.domain.hub_route.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    HUB_ROUTE_NOT_FOUND(HttpStatus.NOT_FOUND, "허브 이동 경로 정보를 찾을 수 없습니다."),
    HUB_ROUTE_UNAUTHORIZED(HttpStatus.NOT_FOUND, "허브 이동 경로 수정에 대한 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
