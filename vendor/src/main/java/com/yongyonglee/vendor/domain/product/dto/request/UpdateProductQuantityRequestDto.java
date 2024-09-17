package com.yongyonglee.vendor.domain.product.dto.request;

import jakarta.validation.constraints.Min;

public record UpdateProductQuantityRequestDto(

        @Min(value = 1, message = "수량은 최소 1이상 설정해주세요.")
        Integer quantity
) {

}
