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
    ORDER_ALREADY_DELETED(400, "이미 삭제된 주문입니다."),

    //delivery
    DELIVERY_NOT_FOUND(404,"존재하지않는 배송입니다." ),
    DELIVERY_ID_NOT_FOUND(404, "배송 id가 존재하지 않습니다."),

    //vendor
    VENDOR_ID_NOT_FOUND(404, "존재하지않는 업체입니다."),

    //hub-route
    RETRIEVE_FAILED(404,"허브 루트를 가져오는 요청을 실패했습니다." ),
    NOT_FOUND_NEXT_HUB(404, "다음 허브를 찾지 못했습니다." );

    private final int status;
    private final String message;
}
