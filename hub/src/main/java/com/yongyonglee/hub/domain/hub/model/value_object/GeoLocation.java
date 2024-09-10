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

    // PostgreSQL의 POINT 타입으로 매핑
    @Column(name = "location", nullable = false, columnDefinition = "POINT")
    private String value;

    public GeoLocation(Double latitude, Double longitude) {
        if (latitude == null || longitude == null) {
            throw new HubException(ExceptionMessage.HUB_GEOLOCATION_NOT_VALID);
        }

        if (latitude < -90.0 || latitude > 90.0) {
            throw new HubException(ExceptionMessage.HUB_LATITUDE_OUT_OF_RANGE);
        }
        if (longitude < -180.0 || longitude > 180.0) {
            throw new HubException(ExceptionMessage.HUB_LONGITUDE_OUT_OF_RANGE);
        }

        this.value = String.format("POINT(%f %f)", longitude, latitude);
    }

    public Double getLatitude() {
        return Double.parseDouble(value.substring(value.indexOf(' ') + 1, value.length() - 1));
    }

    public Double getLongitude() {
        return Double.parseDouble(value.substring(value.indexOf('(') + 1, value.indexOf(' ')));
    }
}
