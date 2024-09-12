package com.yongyonglee.hub.domain.hub.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {

    HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 허브(물류센터)를 찾을 수 없습니다."),
    HUB_GEOLOCATION_NOT_VALID(HttpStatus.NOT_FOUND, "위도, 경도는 필수 입력값입니다."),

    HUB_NAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 사용중인 허브 이름입니다."),

    HUB_LATITUDE_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "위도값의 유효범위는 -90.0과 90.0입니다."),
    HUB_LONGITUDE_OUT_OF_RANGE(HttpStatus.BAD_REQUEST, "경도값의 유효범위는 -180.0과 180.0입니다."),
    HUB_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "허브(물류센터)에 대한 접근 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
