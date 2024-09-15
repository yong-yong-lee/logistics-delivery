package com.yongyonglee.hub.domain.hub_route.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CreateHubRouteRequestDto (
        @NotNull(message = "출발허브의 ID는 필수 입력값입니다.")
        UUID departureId,

        @NotNull(message = "도착허브의 ID는 필수 입력값입니다.")
        UUID arrivalId,

        @NotNull(message = "거리는 필수 입력값입니다.")
        Integer distance,

        @NotNull(message = "소요시간은 필수 입력값입니다.")
        Integer transitTime
) {

}
