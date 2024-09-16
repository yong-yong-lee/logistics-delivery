package com.yongyonglee.order.domain.route.dto;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PUBLIC)
public record HubRouteResponseDto(

        UUID hubRouteId,

        UUID departureId,

        UUID arrivalId,

        Integer distance,

        Integer transitTime
) {

}