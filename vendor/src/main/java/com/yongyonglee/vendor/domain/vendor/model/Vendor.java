package com.yongyonglee.vendor.domain.vendor.model;

import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.dto.request.UpdateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.model.constant.VendorCategory;
import com.yongyonglee.vendor.global.entity.TimeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_vendor")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vendor extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT")
    private Long userId;

    @Column(name = "hub_id", nullable = false)
    private UUID hubId;

    @Column(name = "vendor_name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String vendorName;

    @Column(name = "vendor_category", nullable = false, columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private VendorCategory vendorCategory;

    @Column(name = "vendor_address", nullable = false, columnDefinition = "VARCHAR(100)")
    private String vendorAddress;

    @Builder(access = AccessLevel.PRIVATE)
    public Vendor(Long userId, UUID hubId, String vendorName, VendorCategory vendorCategory,
            String vendorAddress) {
        this.userId = userId;
        this.hubId = hubId;
        this.vendorName = vendorName;
        this.vendorCategory = vendorCategory;
        this.vendorAddress = vendorAddress;
    }

    public static Vendor from(Long userId, VendorCategory vendorCategory, CreateVendorRequestDto requestDto) {

        return Vendor.builder()
                .userId(userId)
                .hubId(requestDto.hubId())
                .vendorName(requestDto.vendorName())
                .vendorAddress(requestDto.vendorAddress())
                .vendorCategory(vendorCategory)
                .build();
    }

    public void updateVendor(UpdateVendorRequestDto requestDto) {
        if (requestDto.userId() != null) {
            this.userId = requestDto.userId();
        }
        if (requestDto.hubId() != null) {
            this.hubId = requestDto.hubId();
        }
        if (requestDto.vendorName() != null) {
            this.vendorName = requestDto.vendorName();
        }
        if (requestDto.vendorCategory() != null) {
            this.vendorCategory = VendorCategory.findByCategory(requestDto.vendorCategory());
        }
        if (requestDto.vendorAddress() != null) {
            this.vendorAddress = requestDto.vendorAddress();
        }
    }

    public void deleteVendor(String userName) {
        super.setDeleted(userName);
    }
}