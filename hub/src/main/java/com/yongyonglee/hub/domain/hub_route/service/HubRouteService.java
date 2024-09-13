package com.yongyonglee.hub.domain.hub_route.service;

import com.yongyonglee.hub.domain.hub_route.dto.request.CreateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.dto.response.HubRouteResponseDto;
import com.yongyonglee.hub.domain.hub_route.model.HubRoute;
import java.util.UUID;

public interface HubRouteService {

    HubRouteResponseDto createHubRoute(CreateHubRouteRequestDto requestDto);

    HubRouteResponseDto getHubRoute(UUID hubRouteId);

    HubRoute findById(UUID hubRouteId);
}
