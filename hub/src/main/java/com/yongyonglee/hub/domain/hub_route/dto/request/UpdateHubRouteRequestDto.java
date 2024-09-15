package com.yongyonglee.hub.domain.hub_route.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateHubRouteRequestDto (

        @NotNull(message = "거리는 필수 입력값입니다.")
        Integer distance,

        @NotNull(message = "소요시간은 필수 입력값입니다.")
        Integer transitTime
){

}
