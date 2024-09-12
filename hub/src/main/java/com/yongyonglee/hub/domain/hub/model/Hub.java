package com.yongyonglee.hub.domain.hub.model;

import com.yongyonglee.hub.domain.hub.dto.request.CreateHubRequestDto;
import com.yongyonglee.hub.domain.hub.dto.request.UpdateHubRequestDto;
import com.yongyonglee.hub.domain.hub.model.value_object.GeoLocation;
import com.yongyonglee.hub.domain.model.TimeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_hub")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hub extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "hub_name", nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String hubName;

    @Column(name = "hub_address", nullable = false, columnDefinition = "VARCHAR(100)")
    private String hubAddress;

    @Embedded
    private GeoLocation geoLocation;

    @Builder(access = AccessLevel.PRIVATE)
    public Hub(String hubName, String hubAddress, GeoLocation geoLocation) {
        this.hubName = hubName;
        this.hubAddress = hubAddress;
        this.geoLocation = geoLocation;
    }

    public static Hub of(CreateHubRequestDto requestDto) {

        return Hub.builder()
                .hubName(requestDto.hubName())
                .hubAddress(requestDto.hubAddress())
                .geoLocation(new GeoLocation(requestDto.latitude(), requestDto.longitude()))
                .build();
    }

    public void updateHub(UpdateHubRequestDto requestDto) {

        if(requestDto.hubName() != null){
            this.hubName = requestDto.hubName();
        }
        if(requestDto.hubAddress() != null){
            this.hubAddress = requestDto.hubAddress();
            this.geoLocation = new GeoLocation(requestDto.latitude(), requestDto.longitude());
        }
    }

    public void deleteHub(String userName) {
        super.setDeleted(userName);
    }
}
