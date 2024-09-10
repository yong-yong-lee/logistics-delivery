package com.yongyonglee.hub.domain.hub_route.model.value_object;

import com.yongyonglee.hub.domain.hub_route.model.converter.TransitTimeToIntervalConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import java.time.Duration;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransitTime {

    @Convert(converter = TransitTimeToIntervalConverter.class)
    @Column(name = "transit_time", nullable = false, columnDefinition = "INTERVAL")
    private Duration value;

    public TransitTime(Duration time) {

        this.value = time;
    }

}
