package com.yongyonglee.vendor.domain.vendor.dto.response;

import com.yongyonglee.vendor.domain.vendor.model.Vendor;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record VendorResponseDto(
        UUID vendorId,
        String vendorName,
        String vendorCategory,
        String vendorAddress,
        UUID hubId,
        LocalDateTime createdAt
){

    public static VendorResponseDto from (Vendor vendor) {
        return VendorResponseDto.builder()
                .vendorId(vendor.getId())
                .vendorName(vendor.getVendorName())
                .vendorCategory(vendor.getVendorCategory().getCategory())
                .vendorAddress(vendor.getVendorAddress())
                .hubId(vendor.getHubId())
                .createdAt(vendor.getCreatedAt())
                .build();
    }
}
