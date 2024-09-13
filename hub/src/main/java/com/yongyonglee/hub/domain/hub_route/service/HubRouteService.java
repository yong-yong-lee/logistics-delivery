package com.yongyonglee.hub.domain.hub_route.service;

import com.yongyonglee.hub.domain.hub_route.dto.request.CreateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.dto.response.HubRouteResponseDto;

public interface HubRouteService {

    HubRouteResponseDto createHubRoute(CreateHubRouteRequestDto requestDto);
}
