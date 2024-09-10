package com.yongyonglee.order.domain.order.entity;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.order.dto.OrderResponse;
import com.yongyonglee.order.global.entity.BaseTimeEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_order")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "demand_id", nullable = false)
    private UUID demandId;

    @Column(name = "supply_id", nullable = false)
    private UUID supplyId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;

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
                .receiverName(delivery.getReceiverName())
                .receiverSlackId(delivery.getReceiverSlackId())
                .build();
    }
}
