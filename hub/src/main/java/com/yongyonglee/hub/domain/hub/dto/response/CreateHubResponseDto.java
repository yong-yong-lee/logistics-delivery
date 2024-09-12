package com.yongyonglee.hub.domain.hub.dto.response;

import com.yongyonglee.hub.domain.hub.model.Hub;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CreateHubResponseDto(
        UUID hubId,
        String hubName,
        String hubAddress,
        Double latitude,
        Double longitude,
        LocalDateTime createdAt
) {

    public static CreateHubResponseDto from(Hub hub) {
        return CreateHubResponseDto.builder()
                .hubId(hub.getId())
                .hubName(hub.getHubName())
                .hubAddress(hub.getHubAddress())
                .latitude(hub.getGeoLocation().getLatitude())
                .longitude(hub.getGeoLocation().getLongitude())
                .createdAt(hub.getCreatedAt())
                .build();
    }
}
