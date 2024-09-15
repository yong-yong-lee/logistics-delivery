package com.yongyonglee.order.domain.route.entity;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import com.yongyonglee.order.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(name = "departure_id")
    private UUID departureId;

    @Column(name = "arrival_id")
    private UUID arrivalId;

    @Setter
    @Column(nullable = false)
    private int sequence;

    @Column(name = "estimated_distance", nullable = false)
    private Integer estimatedDistance;

    @Column(name = "estimated_time", nullable = false)
    private Timestamp estimatedTime;

    @Column(name = "actual_distance")
    private Integer actualDistance;

    @Column(name = "actual_time")
    private Timestamp actualTime;

    @Column(nullable = false)
    private DeliveryStatus status;
}
