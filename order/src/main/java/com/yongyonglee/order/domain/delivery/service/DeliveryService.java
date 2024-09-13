package com.yongyonglee.order.domain.delivery.service;

import com.yongyonglee.order.domain.delivery.dto.DeliveryResponse;
import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import com.yongyonglee.order.domain.delivery.repository.DeliveryRepository;
import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.route.VendorClient;
import com.yongyonglee.order.domain.route.VendorResponseDto;
import com.yongyonglee.order.domain.route.entity.Route;
import com.yongyonglee.order.domain.route.service.RouteService;
import com.yongyonglee.order.global.response.CustomException;
import com.yongyonglee.order.global.response.ErrorCode;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final RouteService routeService;
    private final VendorClient vendorClient;


    public VendorResponseDto getVendorInfo(UUID vendorId) {

        ResponseEntity<VendorResponseDto> response = vendorClient.getVendor(vendorId);

        return response.getBody();
    }

    @Transactional
    public DeliveryResponse addDelivery(OrderCreateRequest orderCreateRequest, UUID orderId) {

        String address = getVendorInfo(orderCreateRequest.getDemandId()).vendorAddress();

        List<Route> routes = routeService.addRoute(orderCreateRequest);

        Delivery delivery = Delivery.builder()
                .orderId(orderId)
                .status(DeliveryStatus.WAITING_AT_HUB)
                .departureId(orderCreateRequest.getSupplyId())
                .arrivalId(orderCreateRequest.getDemandId())
                .vendorAddress(address)
                .receiverName(orderCreateRequest.getReceiverName())
                .receiverSlackId(orderCreateRequest.getReceiverSlackId())
                .routes(routes)
                .build();


        return deliveryRepository.save(delivery).toResponse();
    }

    @Transactional
    public DeliveryResponse getDelivery(UUID deliveryId) {

        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(()-> new CustomException(
                ErrorCode.DELIVERY_ID_NOT_FOUND));

        return delivery.toResponse();
    }

    @Transactional
    public void updateDelivery(UUID deliveryId, String receiverName, String receiverSlackId) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new CustomException(ErrorCode.DELIVERY_NOT_FOUND));

        if (receiverName != null && !receiverName.isEmpty()) {
            delivery.setReceiverName(receiverName);
        }

        if (receiverSlackId != null && !receiverSlackId.isEmpty()) {
            delivery.setReceiverSlackId(receiverSlackId);
        }

        deliveryRepository.save(delivery);
    }
}
