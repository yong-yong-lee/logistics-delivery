package com.yongyonglee.order.domain.order.service;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.delivery.service.DeliveryService;
import com.yongyonglee.order.domain.order.dto.OrderUpdateDto;
import com.yongyonglee.order.domain.order.entity.Order;
import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.order.dto.OrderResponse;
import com.yongyonglee.order.domain.order.repository.OrderRepository;
import com.yongyonglee.order.global.response.CustomException;
import com.yongyonglee.order.global.response.ErrorCode;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryService deliveryService;

    @Transactional
    public OrderResponse addOrder(OrderCreateRequest orderCreateRequest) {

        Order order = orderCreateRequest.toEntity();

        orderRepository.save(order);

        return order.toResponse();
    }

    public OrderResponse getOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .filter(o->!o.isDeleted())
                .orElseThrow(()-> new CustomException(
                ErrorCode.ORDER_ID_NOT_FOUND));

        return order.toResponse();
    }

    public void deleteOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new CustomException(
                ErrorCode.ORDER_ID_NOT_FOUND));

        if (order.isDeleted()) {
            throw new CustomException(ErrorCode.ORDER_ALREADY_DELETED);  // 이미 삭제된 경우 예외 처리
        }

        order.setDeleted();
        order.setDeletedAt(LocalDateTime.now());

    }

    public OrderResponse updateOrder(UUID orderId, OrderUpdateDto orderUpdateDto) {

        Order order = orderRepository.findById(orderId).orElseThrow(()-> new CustomException(
                ErrorCode.ORDER_ID_NOT_FOUND));

        if (orderUpdateDto.getQuantity() > 0){
            order.setQuantity(orderUpdateDto.getQuantity());
        }

        Delivery delivery = order.getDelivery();
        if (delivery != null) {
            deliveryService.updateDelivery(delivery.getId(), orderUpdateDto.getReceiverName(), orderUpdateDto.getReceiverSlackId());
        } else {
            throw new CustomException(ErrorCode.DELIVERY_NOT_FOUND);
        }

        orderRepository.save(order);

        return order.toResponse();

    }
}
