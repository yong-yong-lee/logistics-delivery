package com.yongyonglee.order.domain.order.service;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.order.entity.Order;
import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.order.dto.OrderResponse;
import com.yongyonglee.order.domain.order.repository.OrderRepository;
import com.yongyonglee.order.global.response.CustomException;
import com.yongyonglee.order.global.response.ErrorCode;
import java.util.UUID;
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

        orderRepository.save(order);

        return order.toResponse();
    }

    public OrderResponse getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new CustomException(
                ErrorCode.ORDER_ID_NOT_FOUND));

        return order.toResponse();
    }
}
