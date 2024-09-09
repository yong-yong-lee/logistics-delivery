package com.yongyonglee.hub.domain.hub.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 물류센터를 찾을 수 없습니다."),
    HUB_GEOLOCATION_NOT_VALID(HttpStatus.NOT_FOUND, "위도, 경도는 필수 입력값입니다."),
    HUB_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "물류센터에 대한 접근 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
