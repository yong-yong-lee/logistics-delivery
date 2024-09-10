package com.yongyonglee.order.domain.order.dto;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.order.entity.Order;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateRequest {

    private UUID productId;
    private UUID demandId;
    private UUID supplyId;
    private Integer quantity;
    private String receiverName;
    private String receiverSlackId;

    public Order toEntity() {
        return Order.builder()
                .productId(this.productId)
                .demandId(this.demandId)
                .supplyId(this.supplyId)
                .quantity(this.quantity)
                .build();
    }

}
