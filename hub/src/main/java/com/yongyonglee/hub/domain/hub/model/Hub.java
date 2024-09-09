package com.yongyonglee.hub.domain.hub.model;

import com.yongyonglee.hub.domain.hub.model.value_object.GeoLocation;
import com.yongyonglee.hub.domain.model.TimeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "p_hub")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hub extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "hub_name", nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String hubName;

    @Column(name = "hub_address", nullable = false, columnDefinition = "VARCHAR(100)")
    private String hubAddress;

//    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
//    private Double latitude;
//
//    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
//    private Double longitude;

    @Embedded
    private GeoLocation geoLocation;
}
