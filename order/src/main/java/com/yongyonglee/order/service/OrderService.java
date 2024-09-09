package com.yongyonglee.order.service;

import com.yongyonglee.order.domain.Order;
import com.yongyonglee.order.dto.OrderRequest;
import com.yongyonglee.order.dto.OrderResponse;
import com.yongyonglee.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional()
    public OrderResponse addOrder(OrderRequest orderRequest) {

        Order order = orderRequest.toEntity();

        Order savedOrder = orderRepository.save(order);

        return OrderResponse.toResponse(savedOrder);
    }
}
