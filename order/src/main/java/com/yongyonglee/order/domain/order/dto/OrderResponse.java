package com.yongyonglee.order.domain.order.dto;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.global.entity.BaseTimeEntity;
import java.time.LocalDateTime;
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

    private UUID id;
    private UUID productId;
    private UUID demandId;
    private UUID supplyId;
    private Integer quantity;
    private Delivery delivery;
    private LocalDateTime createdAt;
}
