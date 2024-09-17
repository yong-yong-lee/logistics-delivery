package com.yongyonglee.order.domain.route.dto;

import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class RouteUpdateDto {
    private UUID deliverId;
    private Integer estimatedDistance;
    private Integer estimatedTime;
    private Integer actualDistance;
    private Integer actualTime;
    private DeliveryStatus status;
}
