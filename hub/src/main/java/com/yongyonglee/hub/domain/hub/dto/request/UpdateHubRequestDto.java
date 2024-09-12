package com.yongyonglee.hub.domain.hub.dto.request;

public record UpdateHubRequestDto (

        String hubName,

        String hubAddress,

        Double latitude,

        Double longitude
) {

}
