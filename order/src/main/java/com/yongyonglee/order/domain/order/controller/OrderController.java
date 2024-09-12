package com.yongyonglee.order.domain.order.controller;

import com.yongyonglee.order.domain.delivery.dto.DeliveryResponse;
import com.yongyonglee.order.domain.delivery.service.DeliveryService;
import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.order.dto.OrderResponse;
import com.yongyonglee.order.global.response.ApiResponse;
import com.yongyonglee.order.domain.order.service.OrderService;
import com.yongyonglee.order.global.response.CustomException;
import com.yongyonglee.order.global.response.ErrorCode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final DeliveryService deliveryService;

    @PostMapping()
    public ResponseEntity<ApiResponse<OrderResponse>> addOrder(@RequestBody OrderCreateRequest orderCreateRequest){

        OrderResponse orderResponse = orderService.addOrder(orderCreateRequest);

        DeliveryResponse deliveryResponse = deliveryService.addDelivery(orderCreateRequest, orderResponse.getId());

        if (deliveryResponse == null){
            throw new CustomException(ErrorCode.NOT_FOUND);   //변경 예정
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.of("주문 생성을 성공했습니다", orderResponse));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrder(@PathVariable UUID orderId){

        OrderResponse orderResponse = orderService.getOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.of("주문 조회 성공", orderResponse));
    }
}
