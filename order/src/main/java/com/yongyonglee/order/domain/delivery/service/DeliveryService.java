package com.yongyonglee.order.domain.delivery.service;

import com.yongyonglee.order.domain.delivery.dto.DeliveryResponse;
import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import com.yongyonglee.order.domain.delivery.repository.DeliveryRepository;
import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.route.entity.Route;
import com.yongyonglee.order.domain.route.service.RouteService;
import com.yongyonglee.order.global.response.CustomException;
import com.yongyonglee.order.global.response.ErrorCode;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final RouteService routeService;

    @Transactional
    public DeliveryResponse addDelivery(OrderCreateRequest orderCreateRequest, UUID orderId) {

//        아래부분의 address 조회는 feignClient 이용해야합니다. 현재는 더미 데이터입니다. deliveryRepo가 아니라 vendorRepo.findById(주문요청한 가게 id).getAddress() 해야할듯
        String address = deliveryRepository.findById(orderCreateRequest.getDemandId()).get()
                .getVendorAddress();

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
