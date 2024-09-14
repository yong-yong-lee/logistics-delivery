package com.yongyonglee.vendor.domain.vendor.service;

import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.dto.response.VendorResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VendorService {

    VendorResponseDto createVendor(CreateVendorRequestDto requestDto);

    VendorResponseDto getVendor(UUID vendorId);

    Page<VendorResponseDto> searchVendors(String vendorName, String vendorCategory, UUID hubId, Pageable pageable);

    void deleteVendor(UUID vendorId);
}
