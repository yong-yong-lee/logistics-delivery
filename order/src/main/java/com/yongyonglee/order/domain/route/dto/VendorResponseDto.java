package com.yongyonglee.order.domain.route.dto;


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
){}

