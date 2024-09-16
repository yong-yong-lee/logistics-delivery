package com.yongyonglee.order.domain.route;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public record HubRouteResponseDto (

        UUID hubRouteId,

        UUID departureId,

        UUID arrivalId,

        Integer distance,

        Integer transitTime
) {}