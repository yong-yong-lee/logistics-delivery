package com.yongyonglee.hub.domain.hub_route.service;

import com.yongyonglee.hub.domain.hub_route.dto.request.CreateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.dto.request.UpdateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.dto.response.HubRouteResponseDto;
import com.yongyonglee.hub.domain.hub_route.model.HubRoute;
import java.util.List;
import java.util.UUID;

public interface HubRouteService {

    HubRouteResponseDto createHubRoute(CreateHubRouteRequestDto requestDto);

    HubRouteResponseDto getHubRoute(UUID hubRouteId);

    List<HubRouteResponseDto> getActiveHubRoutes();

    HubRouteResponseDto updateHubRoute(UUID hubRouteId, UpdateHubRouteRequestDto requestDto);

    HubRoute findById(UUID hubRouteId);
}
