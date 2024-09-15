package com.yongyonglee.hub.domain.hub_route.dto.response;

import com.yongyonglee.hub.domain.hub_route.model.HubRoute;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record HubRouteResponseDto (

        UUID hubRouteId,

        UUID departureId,

        UUID arrivalId,

        Integer distance,

        Integer transitTime
) {

    public static HubRouteResponseDto from(HubRoute hubRoute) {
        return HubRouteResponseDto.builder()
                .hubRouteId(hubRoute.getId())
                .departureId(hubRoute.getDepartureHub().getId())
                .arrivalId(hubRoute.getArrivalHub().getId())
                .distance(hubRoute.getDistance())
                .transitTime(hubRoute.getTransitTime())
                .build();
    }

}
