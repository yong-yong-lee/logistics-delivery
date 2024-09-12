package com.yongyonglee.order.domain.order.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateDto {
    private int quantity;
    private String receiverName;
    private String receiverSlackId;

}
