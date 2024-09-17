package com.yongyonglee.order.domain.route.controller;

import com.yongyonglee.order.domain.route.dto.RouteResponseDto;
import com.yongyonglee.order.domain.route.dto.RouteUpdateDto;
import com.yongyonglee.order.domain.route.service.RouteService;
import com.yongyonglee.order.global.response.ApiResponse;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/delivery/{deliveryId}/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<RouteResponseDto>>> getAllHubRoute(
            @PathVariable UUID deliveryId) {
        List<RouteResponseDto> hubRouteResponses = routeService.getHubRoutes(deliveryId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.of("배송 경로 기록 조회 성공", hubRouteResponses));
    }

    @GetMapping("/{sequence}")
    public ResponseEntity<ApiResponse<RouteResponseDto>> getRoute(
            @PathVariable UUID deliveryId, @PathVariable int sequence) {

        RouteResponseDto routeResponse = routeService.getHubRouteBySequence(deliveryId, sequence);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.of("배송 경로 단건 조회 성공", routeResponse));
    }

    @PatchMapping("/{sequence}")
    public ResponseEntity<ApiResponse<RouteResponseDto>> updateRoute(
            @PathVariable UUID deliveryId, @PathVariable int sequence,
            @RequestBody RouteUpdateDto routeUpdateDto) {

        RouteResponseDto updatedRoute = routeService.updateRoute(deliveryId, sequence,
                routeUpdateDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.of("배송 경로 수정 성공", updatedRoute));

    }
}
