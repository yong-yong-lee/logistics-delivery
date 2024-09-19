package com.yongyonglee.order.domain.delivery;


import com.yongyonglee.order.domain.route.dto.VendorResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorResponseWrapper {
    private String message;
    private VendorResponseDto data;
}
