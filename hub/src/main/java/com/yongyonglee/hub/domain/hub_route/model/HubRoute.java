package com.yongyonglee.hub.domain.hub_route.model;

import com.yongyonglee.hub.domain.hub.model.Hub;
import com.yongyonglee.hub.domain.hub_route.dto.request.CreateHubRouteRequestDto;
import com.yongyonglee.hub.domain.model.TimeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_hub_route")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HubRoute extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_id", nullable = false)
    private Hub departureHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_id", nullable = false)
    private Hub arrivalHub;

    @Column(nullable = false)
    private Integer distance;

    @Column(name = "transit_time", nullable = false)
    private Integer transitTime;

    @Builder(access = AccessLevel.PRIVATE)
    public HubRoute(Hub departureHub, Hub arrivalHub, Integer distance, Integer transitTime) {
        this.departureHub = departureHub;
        this.arrivalHub = arrivalHub;
        this.distance = distance;
        this.transitTime = transitTime;
    }

    public static HubRoute of(Hub departureHub, Hub arrivalHub,
            CreateHubRouteRequestDto requestDto) {

        return HubRoute.builder()
                .departureHub(departureHub)
                .arrivalHub(arrivalHub)
                .distance(requestDto.distance())
                .transitTime(requestDto.transitTime())
                .build();
    }
}
