package com.yongyonglee.vendor.domain.vendor.dto.request;

import java.util.UUID;

public record UpdateVendorRequestDto (
        Long userId,
        UUID hubId,
        String vendorName,
        String vendorCategory,
        String vendorAddress
) {

}
