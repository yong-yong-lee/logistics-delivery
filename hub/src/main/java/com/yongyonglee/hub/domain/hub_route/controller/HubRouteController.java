package com.yongyonglee.hub.domain.hub_route.controller;

import static com.yongyonglee.hub.domain.hub_route.message.SuccessMessage.CREATE_HUB_ROUTE_SUCCESS;
import static com.yongyonglee.hub.domain.hub_route.message.SuccessMessage.DELETE_HUB_ROUTE_SUCCESS;
import static com.yongyonglee.hub.domain.hub_route.message.SuccessMessage.GET_HUB_ROUTE_SUCCESS;
import static com.yongyonglee.hub.domain.hub_route.message.SuccessMessage.UPDATE_HUB_ROUTE_SUCCESS;
import static com.yongyonglee.hub.global.response.SuccessResponse.success;

import com.yongyonglee.hub.domain.hub_route.dto.request.CreateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.dto.request.UpdateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.service.HubRouteService;
import com.yongyonglee.hub.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hub-route")
@RequiredArgsConstructor
@Tag(name = "HubRoute-Controller", description = "/api/v1/hub-route")
public class HubRouteController {

    private final HubRouteService hubRouteService;

    /** 허브 루트 생성 api */
    // TODO: 사용자 인증 및 인가(HUB_MANAGER, MASTER) 추가
    @PostMapping("")
    public ResponseEntity<? extends CommonResponse> createHubRoute(@Valid @RequestBody CreateHubRouteRequestDto requestDto) {

        return ResponseEntity.status(CREATE_HUB_ROUTE_SUCCESS.getHttpStatus())
                .body(success(CREATE_HUB_ROUTE_SUCCESS.getMessage(), hubRouteService.createHubRoute(requestDto)));
    }

    /** 허브 루트 단건 조회 api */
    // TODO: 사용자 인증 및 인가(HUB_MANAGER, MASTER) 추가
    @GetMapping("/{hubRouteId}")
    public ResponseEntity<? extends CommonResponse> getHubRoute(@PathVariable UUID hubRouteId) {

        return ResponseEntity.status(GET_HUB_ROUTE_SUCCESS.getHttpStatus())
                .body(success(GET_HUB_ROUTE_SUCCESS.getMessage(), hubRouteService.getHubRoute(hubRouteId)));
    }

    /** 허브 루트 전체 조회 api */
    // TODO: 사용자 인증 및 인가(HUB_MANAGER, MASTER) 추가
    @GetMapping("")
    public ResponseEntity<? extends CommonResponse> getHubRoutes() {

        return ResponseEntity.status(GET_HUB_ROUTE_SUCCESS.getHttpStatus())
                .body(success(GET_HUB_ROUTE_SUCCESS.getMessage(), hubRouteService.getActiveHubRoutes()));
    }

    /** 허브 루트 수정 api */
    // TODO: 사용자 인증 및 인가(HUB_MANAGER, MASTER) 추가
    @PutMapping("/{hubRouteId}")
    public ResponseEntity<? extends CommonResponse> updateHubRoute(@PathVariable UUID hubRouteId, @RequestBody UpdateHubRouteRequestDto requestDto) {

        return ResponseEntity.status(UPDATE_HUB_ROUTE_SUCCESS.getHttpStatus())
                .body(success(UPDATE_HUB_ROUTE_SUCCESS.getMessage(), hubRouteService.updateHubRoute(hubRouteId, requestDto)));
    }

    /** 허브 루트 삭제 api */
    // TODO: 사용자 인증 및 인가(HUB_MANAGER, MASTER) 추가
    @DeleteMapping("/{hubRouteId}")
    public ResponseEntity<? extends CommonResponse> deleteHubRoute(@PathVariable(name = "hubRouteId") UUID hubRouteId) {

        hubRouteService.deleteHubRoute(hubRouteId);

        return ResponseEntity.status(DELETE_HUB_ROUTE_SUCCESS.getHttpStatus())
                .body(success(DELETE_HUB_ROUTE_SUCCESS.getMessage()));
    }
}
