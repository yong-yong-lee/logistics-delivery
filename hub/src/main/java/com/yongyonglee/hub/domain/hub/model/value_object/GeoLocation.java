package com.yongyonglee.hub.domain.hub.model.value_object;

import com.yongyonglee.hub.domain.hub.exception.HubException;
import com.yongyonglee.hub.domain.hub.message.ExceptionMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class GeoLocation {

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    public GeoLocation(Double latitude, Double longitude) {

        if(latitude == null || longitude == null) {
            throw new HubException(ExceptionMessage.HUB_GEOLOCATION_NOT_VALID);
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }
}
