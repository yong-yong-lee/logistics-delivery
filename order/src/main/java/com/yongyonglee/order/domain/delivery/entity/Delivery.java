package com.yongyonglee.order.domain.delivery.entity;

import com.yongyonglee.order.domain.delivery.dto.DeliveryResponse;
import com.yongyonglee.order.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @JoinColumn(name = "order_id")
    private UUID orderId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;///////다시

    @Column(name = "departure_id", nullable = false)
    private UUID departureId;

    @Column(name = "arrival_id", nullable = false)
    private UUID arrivalId;

    @Column(name = "status", nullable = false)
    private DeliveryStatus status = DeliveryStatus.WAITING_AT_HUB;

    @Column(name = "vendor_address", nullable = false)
    private String vendorAddress;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_slack_id", nullable = false)
    private String receiverSlackId;

    public DeliveryResponse toResponse() {
        return DeliveryResponse.builder()
                .id(id)
                .status(status)
                .departureId(departureId)
                .arrivalId(arrivalId)
                .address(vendorAddress)
                .receiverName(receiverName)
                .receiverSlackId(receiverSlackId)
                .build();
    }
}
