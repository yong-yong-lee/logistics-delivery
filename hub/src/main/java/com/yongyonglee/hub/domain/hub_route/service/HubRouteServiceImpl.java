package com.yongyonglee.hub.domain.hub_route.service;

import com.yongyonglee.hub.domain.hub.model.Hub;
import com.yongyonglee.hub.domain.hub.service.HubService;
import com.yongyonglee.hub.domain.hub_route.dto.request.CreateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.dto.response.HubRouteResponseDto;
import com.yongyonglee.hub.domain.hub_route.model.HubRoute;
import com.yongyonglee.hub.domain.hub_route.repository.HubRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HubRouteServiceImpl implements HubRouteService{

    private final HubRouteRepository hubRouteRepository;
    private final HubService hubService;

    @Override
    public HubRouteResponseDto createHubRoute(CreateHubRouteRequestDto requestDto) {

        Hub departureHub = hubService.findById(requestDto.departureId());
        Hub arrivalHub = hubService.findById(requestDto.arrivalId());

        HubRoute hubRoute = hubRouteRepository.save(HubRoute.of(departureHub, arrivalHub, requestDto));

        return HubRouteResponseDto.from(hubRoute);
    }
}
