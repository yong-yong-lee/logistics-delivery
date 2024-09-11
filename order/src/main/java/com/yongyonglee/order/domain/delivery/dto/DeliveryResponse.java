package com.yongyonglee.order.domain.delivery.dto;

import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class DeliveryResponse {

    private UUID id;
    private UUID orderId;
    private DeliveryStatus status;
    private UUID departureId;
    private UUID arrivalId;
    private String address;
    private String receiverName;
    private String receiverSlackId;

}
