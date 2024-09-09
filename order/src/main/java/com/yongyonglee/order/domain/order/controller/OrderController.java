package com.yongyonglee.order.domain.order.controller;

import com.yongyonglee.order.domain.order.dto.OrderRequest;
import com.yongyonglee.order.domain.order.dto.OrderResponse;
import com.yongyonglee.order.global.ApiResponse;
import com.yongyonglee.order.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<ApiResponse<OrderResponse>> addOrder(@RequestBody OrderRequest orderRequest){

        OrderResponse orderResponse = orderService.addOrder(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.of("주문 생성 성공", orderResponse));
    }
}
