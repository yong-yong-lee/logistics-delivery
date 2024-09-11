package com.yongyonglee.order.domain.delivery.controller;

import com.yongyonglee.order.domain.delivery.dto.DeliveryResponse;
import com.yongyonglee.order.domain.delivery.service.DeliveryService;
import com.yongyonglee.order.global.response.ApiResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
@RestController
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<DeliveryResponse>> getDelivery(@PathVariable UUID deliveryId){

        DeliveryResponse deliveryResponse = deliveryService.getDelivery(deliveryId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.of("배송 조회 성공", deliveryResponse));
    }
}
