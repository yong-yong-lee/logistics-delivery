package com.yongyonglee.hub.domain.hub.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record CreateHubRequestDto (
        @NotBlank(message = "허브 이름은 필수 입력값입니다.")
        @Size(max = 100, message = "Hub name must not exceed 100 characters")
        String hubName,

        @NotBlank(message = "허브 주소는 필수 입력값입니다.")
        @Size(max = 100, message = "허브 주소는 100자를 초과할 수 없습니다.")
        String hubAddress,

        @NotNull(message = "위도는 필수 입력값입니다.")
        @Min(value = -90, message = "위도의 유효범위는 -90이상 90이하 입니다.")
        @Max(value = 90, message = "위도의 유효범위는 -90이상 90이하 입니다.")
        Double latitude,

        @NotNull(message = "경도는 필수 입력값입니다.")
        @Min(value = -180, message = "경도의 유효범위는 -180이상 180이하 입니다.")
        @Max(value = 180, message = "경도의 유효범위는 -180이상 180이하 입니다.")
        Double longitude
) {

}
