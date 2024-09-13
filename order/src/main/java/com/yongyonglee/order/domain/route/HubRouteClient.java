package com.yongyonglee.order.domain.route;

import com.yongyonglee.order.domain.route.entity.Route;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service")
public interface HubRouteClient {

    //todo endpoint 맞는지 확인해야합니다.
    @GetMapping("/api/v1/hub-route")
    ResponseEntity<List<HubRouteResponse>> getAllHubRoutes();
}
