package com.yongyonglee.order.domain.order.dto;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.order.entity.Order;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private UUID productId;
    private UUID demandId;
    private UUID supplyId;
    private Integer quantity;
    private String receiverName;
    private String receiverSlackId;

    public Order toEntity(){

        Delivery delivery = Delivery.builder()
                .receiverName(receiverName)
                .receiverSlackId(receiverSlackId)
                .build();

        return Order.builder()
                .productId(productId)
                .demandId(demandId)
                .supplyId(supplyId)
                .quantity(quantity)
                .delivery(delivery)
                .build();
    }

}
