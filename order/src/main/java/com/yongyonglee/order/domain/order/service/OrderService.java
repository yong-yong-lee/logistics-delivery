package com.yongyonglee.order.domain.order.service;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.order.entity.Order;
import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.order.dto.OrderResponse;
import com.yongyonglee.order.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponse addOrder(OrderCreateRequest orderCreateRequest) {

        Order order = orderCreateRequest.toEntity();

        Delivery delivery = Delivery.builder()
                .receiverName(orderCreateRequest.getReceiverName())
                .receiverSlackId(orderCreateRequest.getReceiverSlackId())
                .build();

        order.setDelivery(delivery);

        orderRepository.save(order);

        return order.toResponse();
    }
}
