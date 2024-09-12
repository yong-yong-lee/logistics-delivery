package com.yongyonglee.order.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND(404, "Resource not found."),
    BAD_REQUEST(400, "Bad request."),
    UNAUTHORIZED(401, "Unauthorized access."),
    FORBIDDEN(403, "Forbidden."),
    INTERNAL_SERVER_ERROR(500, "Internal server error."),
    INVALID_REQUEST_BODY(400, "잘못된 요청 입니다."),

    //order
    ORDER_ID_NOT_FOUND(404, "주문 id 가 존재하지 않습니다."),
    DELIVERY_ID_NOT_FOUND(404, "배송 id가 존재하지 않습니다."),
    ORDER_ALREADY_DELETED(400, "이미 삭제된 주문입니다.");

    private final int status;
    private final String message;
}
