package com.yongyonglee.vendor.domain.vendor.model.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VendorCategory {

    DEMAND_VENDOR("주문업체"),
    SUPPLY_VENDOR("공급업체");

    private final String category;

}
