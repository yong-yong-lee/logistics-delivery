package com.yongyonglee.order.domain.order.entity;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.order.dto.OrderResponse;
import com.yongyonglee.order.global.entity.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity(name = "p_order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "demand_id", nullable = false)
    private UUID demandId;

    @Column(name = "supply_id", nullable = false)
    private UUID supplyId;

    @Setter
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public OrderResponse toResponse() {
        return OrderResponse.builder()
                .id(id)
                .productId(productId)
                .demandId(demandId)
                .supplyId(supplyId)
                .quantity(quantity)
                .createdAt(getCreatedAt())
                .build();
    }

    public void setDeleted() {
        this.isDeleted = true;
        setDeletedAt(LocalDateTime.now());
        setDeletedBy("삭제한 사용자"); //todo 삭제한 사용자 넣어야해요
    }
}
