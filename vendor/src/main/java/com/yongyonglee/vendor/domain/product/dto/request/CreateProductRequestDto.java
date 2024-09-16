package com.yongyonglee.vendor.domain.product.dto.request;

import com.yongyonglee.vendor.domain.product.model.Product;
import com.yongyonglee.vendor.domain.vendor.model.Vendor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CreateProductRequestDto(

        @NotBlank(message = "상품명은 필수 입력값입니다.")
        @Size(max = 100, message = "상품명는 100자를 초과할 수 없습니다.")
        String productName,

        @NotBlank(message = "업체의 Id는 필수 입력값입니다.")
        UUID vendorId,

        @NotNull(message = "수량은 필수 입력값입니다.")
        @Min(value = 1, message = "수량은 최소 1이상 설정해주세요.")
        Integer quantity
){

        public Product toEntity(Vendor vendor) {
                return Product.builder()
                        .vendor(vendor)
                        .hubId(vendor.getHubId())
                        .productName(productName)
                        .quantity(quantity)
                        .build();
        }
}
