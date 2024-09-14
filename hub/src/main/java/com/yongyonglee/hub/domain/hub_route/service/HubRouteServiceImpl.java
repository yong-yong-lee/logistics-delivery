package com.yongyonglee.hub.domain.hub_route.service;

import com.yongyonglee.hub.domain.hub.model.Hub;
import com.yongyonglee.hub.domain.hub.service.HubService;
import com.yongyonglee.hub.domain.hub_route.dto.request.CreateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.dto.request.UpdateHubRouteRequestDto;
import com.yongyonglee.hub.domain.hub_route.dto.response.HubRouteResponseDto;
import com.yongyonglee.hub.domain.hub_route.message.ExceptionMessage;
import com.yongyonglee.hub.domain.hub_route.exception.HubRouteException;
import com.yongyonglee.hub.domain.hub_route.model.HubRoute;
import com.yongyonglee.hub.domain.hub_route.repository.HubRouteRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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

    @Override
    public HubRouteResponseDto getHubRoute(UUID hubRouteId) {

        HubRoute hubRoute = findById(hubRouteId);

        return HubRouteResponseDto.from(hubRoute);
    }

    @Override
    public List<HubRouteResponseDto> getActiveHubRoutes() {

        return hubRouteRepository.findAllActiveHubRoutes()
                .stream()
                .map(HubRouteResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public HubRouteResponseDto updateHubRoute(UUID hubRouteId, UpdateHubRouteRequestDto requestDto) {

        HubRoute hubRoute = findById(hubRouteId);

//        if(user.getHubId() != hubRoute.getDepartureHub().getId()) {
//            throw new HubRouteException(ExceptionMessage.HUB_ROUTE_UNAUTHORIZED);
//        }
        hubRoute.updateHubRoute(requestDto);

        return HubRouteResponseDto.from(hubRoute);
    }

    @Override
    public HubRoute findById(UUID hubRouteId) {

        return hubRouteRepository.findByIdAndIsDeletedFalse(hubRouteId)
                .orElseThrow(() -> new HubRouteException(ExceptionMessage.HUB_ROUTE_NOT_FOUND));
    }
}
