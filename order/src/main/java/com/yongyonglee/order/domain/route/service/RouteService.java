package com.yongyonglee.order.domain.route.service;

import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.route.HubRouteClient;

import com.yongyonglee.order.domain.route.HubRouteResponseDto;
import com.yongyonglee.order.domain.route.VendorClient;
import com.yongyonglee.order.domain.route.VendorResponseDto;
import com.yongyonglee.order.domain.route.entity.Route;
import com.yongyonglee.order.global.response.CustomException;
import com.yongyonglee.order.global.response.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RouteService {

    private final HubRouteClient hubRouteClient;
    private final VendorClient vendorClient;

    public VendorResponseDto getVendorInfo(UUID vendorId) {

        return Optional.ofNullable(vendorClient.getVendor(vendorId).getBody())
                .orElseThrow(() -> new CustomException(ErrorCode.VENDOR_ID_NOT_FOUND));
    }

    public List<HubRouteResponseDto> getAllHubRoutes() {
        return Optional.ofNullable(hubRouteClient.getAllHubRoutes().getBody())
                .orElseThrow(() -> new CustomException(ErrorCode.RETRIEVE_FAILED));
    }


    public List<Route> addRoute(OrderCreateRequest orderCreateRequest) {

        UUID startHub = getVendorInfo(orderCreateRequest.getSupplyId()).hubId();

        UUID endHub = getVendorInfo(orderCreateRequest.getDemandId()).hubId();

        List<HubRouteResponseDto> hubRoutes = getAllHubRoutes();

        return findRoutesBetweenHubs(startHub, endHub, hubRoutes);
    }

    private List<Route> findRoutesBetweenHubs(UUID startHub, UUID endHub,
            List<HubRouteResponseDto> hubRoutes) {
        List<Route> routeList = new ArrayList<>();
        UUID currentHub = startHub;
        int sequence = 1;

        while (!currentHub.equals(endHub)) {
            HubRouteResponseDto nextHubRoute = findNextHubRoute(currentHub, hubRoutes)
                    .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_NEXT_HUB));

            Route route = buildRoute(nextHubRoute, sequence++);
            routeList.add(route);

            currentHub = nextHubRoute.arrivalId();
        }

        return routeList;
    }

    private Optional<HubRouteResponseDto> findNextHubRoute(UUID currentHub, List<HubRouteResponseDto> hubRoutes) {
        return hubRoutes.stream()
                .filter(hubRoute -> hubRoute.departureId().equals(currentHub))
                .findFirst();
    }

    private Route buildRoute(HubRouteResponseDto hubRoute, int sequence) {
        return Route.builder()
                .departureId(hubRoute.departureId())
                .arrivalId(hubRoute.arrivalId())
                .sequence(sequence)
                .estimatedDistance(hubRoute.distance())
                .estimatedTime(hubRoute.transitTime())
                .status(DeliveryStatus.BEFORE_DELIVERY)
                .build();
    }
}
