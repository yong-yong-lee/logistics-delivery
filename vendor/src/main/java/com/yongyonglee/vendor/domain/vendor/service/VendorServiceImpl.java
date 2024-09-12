package com.yongyonglee.vendor.domain.vendor.service;

import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.dto.response.VendorResponseDto;
import com.yongyonglee.vendor.domain.vendor.exception.VendorException;
import com.yongyonglee.vendor.domain.vendor.message.ExceptionMessage;
import com.yongyonglee.vendor.domain.vendor.model.Vendor;
import com.yongyonglee.vendor.domain.vendor.model.constant.VendorCategory;
import com.yongyonglee.vendor.domain.vendor.repository.VendorRepository;
import java.util.UUID;
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

        // TODO: userId 가져오는 부분과 HubId 유효성 검사 로직 추가하기
        Vendor vendor = vendorRepository.save(Vendor.from(1L, vendorCategory, requestDto));

        return VendorResponseDto.from(vendor);
    }

    @Override
    public VendorResponseDto getVendor(UUID vendorId) {

        Vendor vendor = findById(vendorId);

        return VendorResponseDto.from(vendor);
    }

    public Vendor findById(UUID vendorId) {
        return vendorRepository.findByIdAndIsDeletedFalse(vendorId)
                .orElseThrow(() -> new VendorException(ExceptionMessage.VENDOR_NOT_FOUND));
    }
}
