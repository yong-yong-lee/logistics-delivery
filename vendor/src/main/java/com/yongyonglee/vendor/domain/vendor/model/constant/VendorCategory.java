package com.yongyonglee.vendor.domain.vendor.model.constant;

import com.yongyonglee.vendor.domain.vendor.exception.VendorException;
import com.yongyonglee.vendor.domain.vendor.message.ExceptionMessage;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VendorCategory {

    DEMAND_VENDOR("주문업체"),
    SUPPLY_VENDOR("공급업체");

    private final String category;

    public static VendorCategory findByCategory(String category) {

        return Arrays.stream(VendorCategory.values())
                .filter(v -> v.getCategory().equals(category))
                .findFirst()
                .orElseThrow(() -> new VendorException(ExceptionMessage.VENDOR_CATEGORY_INVALID));
    }
}
