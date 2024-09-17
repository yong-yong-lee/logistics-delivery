package com.yongyonglee.order.domain.route.dto;

import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import com.yongyonglee.order.domain.route.entity.Route;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Builder
public class RouteResponseDto {
    private UUID id;
    private UUID deliveryId;
    private UUID departureId;
    private UUID arrivalId;
    private int sequence;
    private Integer estimatedDistance;
    private Integer estimatedTime;
    private Integer actualDistance;
    private Integer actualTime;
    private DeliveryStatus status;

    public static RouteResponseDto from(Route route) {
        return RouteResponseDto.builder()
                .id(route.getId())
                .deliveryId(route.getDelivery().getId())
                .departureId(route.getDepartureId())
                .arrivalId(route.getArrivalId())
                .sequence(route.getSequence())
                .estimatedDistance(route.getEstimatedDistance())
                .estimatedTime(route.getEstimatedTime())
                .actualDistance(route.getActualDistance())
                .actualTime(route.getActualTime())
                .status(route.getStatus())
                .build();
    }
}

