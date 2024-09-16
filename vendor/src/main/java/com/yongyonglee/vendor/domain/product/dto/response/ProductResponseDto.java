package com.yongyonglee.vendor.domain.product.dto.response;

import com.yongyonglee.vendor.domain.product.model.Product;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ProductResponseDto(
        UUID productId,
        UUID vendorId,
        UUID hubId,
        String productName,
        Integer quantity
) {

    public static ProductResponseDto from(Product product) {
        return ProductResponseDto.builder()
                .productId(product.getId())
                .vendorId(product.getVendor().getId())
                .hubId(product.getHubId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .build();
    }
}
