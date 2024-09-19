package com.yongyonglee.order.domain.route;

import com.yongyonglee.order.domain.route.dto.HubRouteResponseDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "hub-service")
public interface HubRouteClient {


    @GetMapping("/api/v1/hub-route")
    ResponseEntity<HubRouteResponseWrapper> getHubRoutes();
}
