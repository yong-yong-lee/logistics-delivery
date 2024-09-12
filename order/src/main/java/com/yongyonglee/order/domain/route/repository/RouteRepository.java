package com.yongyonglee.order.domain.route.repository;

import com.yongyonglee.order.domain.route.entity.Route;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, UUID> {

}
