package com.yongyonglee.order.domain.delivery.entity;

import com.yongyonglee.order.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Delivery extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "delivery_id", nullable = false)
    private UUID id;

    @Column(name = "productId", nullable = false)
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


    @Builder
    public Delivery(String receiverName, String receiverSlackId){
        this.receiverName = receiverName;
        this.receiverSlackId = receiverSlackId;
    }
}
