package com.yongyonglee.vendor.domain.vendor.service;

import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.dto.response.VendorResponseDto;
import com.yongyonglee.vendor.domain.vendor.model.Vendor;
import com.yongyonglee.vendor.domain.vendor.model.constant.VendorCategory;
import com.yongyonglee.vendor.domain.vendor.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public VendorResponseDto createVendor(CreateVendorRequestDto requestDto) {

        VendorCategory vendorCategory = VendorCategory.findByCategory(requestDto.vendorCategory());

        Vendor vendor = vendorRepository.save(Vendor.from(1L, vendorCategory, requestDto));

        return VendorResponseDto.from(vendor);
    }
}
