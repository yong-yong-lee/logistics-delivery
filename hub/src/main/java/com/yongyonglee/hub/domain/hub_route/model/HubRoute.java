package com.yongyonglee.hub.domain.hub_route.model;

import com.yongyonglee.hub.domain.hub.model.Hub;
import com.yongyonglee.hub.domain.model.TimeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_hub_route")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HubRoute extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_id", nullable = false)
    private Hub departureHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_id", nullable = false)
    private Hub arrivalHub;

    @Column(nullable = false)
    private Integer distance;

    @Column(nullable = false)
    private Timestamp time;
}
