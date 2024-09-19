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
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "p_route")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(name = "deliver_id")
    private UUID deliverId;

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

    @Setter
    @Column(name = "estimated_distance", nullable = false)
    private Integer estimatedDistance;

    @Setter
    @Column(name = "estimated_time", nullable = false)
    private Integer estimatedTime;

    @Setter
    @Column(name = "actual_distance")
    private Integer actualDistance;

    @Setter
    @Column(name = "actual_time")
    private Integer actualTime;

    @Setter
    @Column(nullable = false)
    private DeliveryStatus status;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public void setDeleted() {
        this.isDeleted = true;
        setDeletedAt(LocalDateTime.now());
        setDeletedBy("삭제한 사용자"); //todo 삭제한 사용자 넣어야해요
    }
}
