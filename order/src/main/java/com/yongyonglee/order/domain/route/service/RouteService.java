package com.yongyonglee.order.domain.route.service;

import com.yongyonglee.order.domain.delivery.entity.DeliveryStatus;
import com.yongyonglee.order.domain.order.dto.OrderCreateRequest;
import com.yongyonglee.order.domain.route.HubRouteClient;

import com.yongyonglee.order.domain.route.HubRouteResponseWrapper;
import com.yongyonglee.order.domain.route.dto.HubRouteResponseDto;
import com.yongyonglee.order.domain.route.VendorClient;
import com.yongyonglee.order.domain.route.dto.VendorResponseDto;
import com.yongyonglee.order.domain.route.dto.RouteResponseDto;
import com.yongyonglee.order.domain.route.dto.RouteUpdateDto;
import com.yongyonglee.order.domain.route.entity.Route;
import com.yongyonglee.order.domain.route.repository.RouteRepository;
import com.yongyonglee.order.global.response.CustomException;
import com.yongyonglee.order.global.response.ErrorCode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RouteService {

    private static final Logger log = LoggerFactory.getLogger(RouteService.class);
    private final HubRouteClient hubRouteClient;
    private final VendorClient vendorClient;
    private final RouteRepository routeRepository;

    public VendorResponseDto getVendorInfo(UUID vendorId) {

        return Optional.ofNullable(
                        Objects.requireNonNull(vendorClient.getVendor(vendorId).getBody()).getData())
                .orElseThrow(() -> new CustomException(ErrorCode.VENDOR_ID_NOT_FOUND));
    }

    public List<HubRouteResponseDto> getAllHubRoutes() {
        return Optional.ofNullable(hubRouteClient.getHubRoutes().getBody())
                .map(HubRouteResponseWrapper::getData)
                .orElseThrow(() -> new CustomException(ErrorCode.RETRIEVE_FAILED));
    }

    //배송경로 추가
    @Transactional
    public List<Route> addRoute(OrderCreateRequest orderCreateRequest) {

        UUID startHub = getVendorInfo(orderCreateRequest.getSupplyId()).hubId();

        UUID endHub = getVendorInfo(orderCreateRequest.getDemandId()).hubId();

        List<HubRouteResponseDto> hubRoutes = getAllHubRoutes();

        return findRoutesBetweenHubs(startHub, endHub, hubRoutes);
    }

    private List<Route> findRoutesBetweenHubs(UUID startHub, UUID endHub,
            List<HubRouteResponseDto> hubRoutes) {

        // 먼저 정방향 탐색 시도
        List<Route> forwardRoutes = findRoutes(startHub, endHub, hubRoutes);
        if (!forwardRoutes.isEmpty()) {
            return forwardRoutes;
        }

        // 정방향 탐색 실패 시 역방향 탐색 시도
        log.info("정방향 탐색 실패. 역방향 탐색을 시도합니다.");
        List<HubRouteResponseDto> reversedHubRoutes = reverseHubRoutes(hubRoutes);
        List<Route> backwardRoutes = findRoutes(endHub, startHub, reversedHubRoutes);
        if (!backwardRoutes.isEmpty()) {
            // 역방향 경로를 원래 순서로 되돌립니다.
            Collections.reverse(backwardRoutes);
            return backwardRoutes;
        }

        throw new CustomException(ErrorCode.NOT_FOUND_NEXT_HUB);
    }

    private List<Route> findRoutes(UUID startHub, UUID endHub,
            List<HubRouteResponseDto> hubRoutes) {
        List<Route> routeList = new ArrayList<>();
        UUID currentHub = startHub;
        int sequence = 1;

        while (!currentHub.equals(endHub)) {
            HubRouteResponseDto nextHubRoute = findNextHubRoute(currentHub, hubRoutes)
                    .orElseThrow(() -> {
                        return new CustomException(ErrorCode.NOT_FOUND_NEXT_HUB);
                    });

            Route route = buildRoute(nextHubRoute, sequence++);
            routeList.add(route);

            currentHub = nextHubRoute.arrivalId();
        }

        return routeList;
    }

    private List<HubRouteResponseDto> reverseHubRoutes(List<HubRouteResponseDto> hubRoutes) {
        return hubRoutes.stream()
                .map(hubRoute -> new HubRouteResponseDto(
                        hubRoute.hubRouteId(),
                        hubRoute.arrivalId(),
                        hubRoute.departureId(),
                        hubRoute.distance(),
                        hubRoute.transitTime()
                ))
                .collect(Collectors.toList());
    }


    private Optional<HubRouteResponseDto> findNextHubRoute(UUID currentHub,
            List<HubRouteResponseDto> hubRoutes) {
        log.info("Searching for next hub route from currentHub: {}", currentHub);
        return hubRoutes.stream()
                .peek(hubRoute -> log.info("Checking hubRoute with departureId: {}", hubRoute.departureId()))
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

    //    한 배송 id 에 대한 모든 배송경로 기록 조회
    @Transactional
    public List<RouteResponseDto> getHubRoutes(UUID deliveryId) {
        List<Route> routes = routeRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId);

        return routes.stream()
                .map(RouteResponseDto::from)
                .collect(Collectors.toList());
    }

    //    배송경로 단건 조회
    @Transactional
    public RouteResponseDto getHubRouteBySequence(UUID deliveryId, int sequence) {
        Route route = routeRepository.findByDeliveryIdAndSequenceAndIsDeletedFalse(deliveryId, sequence)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ROUTE));

        return RouteResponseDto.from(route);
    }

    // 배송경로 수정(단건만 가능)
    @Transactional
    public RouteResponseDto updateRoute(UUID deliveryId, int sequence,
            RouteUpdateDto updateRequest) {

        Route route = routeRepository.findByDeliveryIdAndSequenceAndIsDeletedFalse(deliveryId, sequence)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ROUTE));

        if (updateRequest.getEstimatedDistance() != null) {
            route.setEstimatedDistance(updateRequest.getEstimatedDistance());
        }
        if (updateRequest.getEstimatedTime() != null) {
            route.setEstimatedTime(updateRequest.getEstimatedTime());
        }
        if (updateRequest.getActualDistance() != null) {
            route.setActualDistance(updateRequest.getActualDistance());
        }
        if (updateRequest.getActualTime() != null) {
            route.setActualTime(updateRequest.getActualTime());
        }
        if (updateRequest.getStatus() != null) {
            try {
                DeliveryStatus status = DeliveryStatus.valueOf(
                        String.valueOf(updateRequest.getStatus()));
                route.setStatus(status);
            } catch (IllegalArgumentException e) {
                throw new CustomException(ErrorCode.INVALID_STATUS);
            }
        }
        if (updateRequest.getDeliverId() != null) {
            route.setDeliverId(updateRequest.getDeliverId());
        }

        routeRepository.save(route);

        return RouteResponseDto.from(route);
    }

    @Transactional
    public void deleteRoutesByDeliveryId(UUID deliveryId) {
        List<Route> routes = routeRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId);

        if (routes.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_ROUTE);
        }

        for (Route route : routes) {
            route.setDeleted();
        }

        routeRepository.saveAll(routes);
    }
}
