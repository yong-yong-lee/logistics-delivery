package com.yongyonglee.order.dto;

import com.yongyonglee.order.domain.Delivery;
import com.yongyonglee.order.domain.Order;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderResponse {

    private UUID Id;
    private UUID productId;
    private UUID demandId;
    private UUID supplyId;
    private Integer quantity;
    private String receiverName;
    private String receiverSlackId;
    private Delivery delivery;

    public static OrderResponse toResponse(Order order){
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setProductId(order.getProductId());
        response.setDemandId(order.getDemandId());
        response.setSupplyId(order.getSupplyId());
        response.setQuantity(order.getQuantity());

        if (order.getDelivery() != null) {
            response.setReceiverName(order.getDelivery().getReceiverName());
            response.setReceiverSlackId(order.getDelivery().getReceiverSlackId());
        }

        return response;
    }
}
