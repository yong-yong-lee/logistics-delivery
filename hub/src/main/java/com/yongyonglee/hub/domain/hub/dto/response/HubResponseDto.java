package com.yongyonglee.hub.domain.hub.dto.response;

import com.yongyonglee.hub.domain.hub.model.Hub;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record HubResponseDto (
        UUID hubId,
        String hubName,
        String hubAddress,
        Double latitude,
        Double longitude,
        LocalDateTime createdAt
) implements Serializable {

    public static HubResponseDto from(Hub hub) {
        return HubResponseDto.builder()
                .hubId(hub.getId())
                .hubName(hub.getHubName())
                .hubAddress(hub.getHubAddress())
                .latitude(hub.getGeoLocation().getLatitude())
                .longitude(hub.getGeoLocation().getLongitude())
                .createdAt(hub.getCreatedAt())
                .build();
    }
}
