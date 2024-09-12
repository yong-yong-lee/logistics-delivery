package com.yongyonglee.vendor.domain.vendor.service;

import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.dto.response.VendorResponseDto;
import java.util.UUID;

public interface VendorService {

    VendorResponseDto createVendor(CreateVendorRequestDto requestDto);

    VendorResponseDto getVendor(UUID vendorId);
}
