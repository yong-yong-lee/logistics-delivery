package com.yongyonglee.order.domain.delivery.entity;

public enum DeliveryStatus {
    WAITING_AT_HUB("허브 대기중"),
    IN_DELIVERY("배송중"),
    OUT_FOR_DELIVERY("배송지로 배송중"),
    DELIVERED("배송완료");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


