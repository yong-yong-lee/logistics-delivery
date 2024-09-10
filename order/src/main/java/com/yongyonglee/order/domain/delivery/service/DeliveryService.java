package com.yongyonglee.order.domain.delivery.service;

import com.yongyonglee.order.domain.delivery.dto.DeliveryResponse;
import com.yongyonglee.order.domain.delivery.entity.Delivery;
import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import com.yongyonglee.order.domain.delivery.repository.DeliveryRepository;
import com.yongyonglee.order.domain.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryResponse addDelivery(OrderResponse orderResponse) {

//        아래부분의 address 조회는 feignClient 이용해야합니다. 현재는 더미 데이터입니다. deliveryRepo가 아니라 vendorRepo.findById(주문요청한 가게 id).getAddress() 해야할듯
        String address = deliveryRepository.findById(orderResponse.getDemandId()).get()
                .getVendorAddress();

        Delivery delivery = Delivery.builder()
                .order(orderResponse.getId())
                .status(DeliveryStatus.WAITING_AT_HUB)
                .departureId(orderResponse.getSupplyId())
                .arrivalId(orderResponse.getDemandId())
                .vendorAddress(address)
                .receiverName(orderResponse.getReceiverName())
                .receiverSlackId(orderResponse.getReceiverSlackId())
                .build();

        return deliveryRepository.save(delivery).toResponse();
    }
}
