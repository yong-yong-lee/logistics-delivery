package com.yongyonglee.vendor.domain.vendor.service;

import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.dto.response.VendorResponseDto;

public interface VendorService {

    VendorResponseDto createVendor(CreateVendorRequestDto requestDto);
}
