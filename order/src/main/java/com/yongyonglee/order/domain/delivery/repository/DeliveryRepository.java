package com.yongyonglee.order.domain.delivery.repository;

import com.yongyonglee.order.domain.delivery.entity.Delivery;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

}
