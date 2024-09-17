package com.yongyonglee.order.domain.delivery.dto;

import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryUpdateDto {
    private DeliveryStatus status;
}
