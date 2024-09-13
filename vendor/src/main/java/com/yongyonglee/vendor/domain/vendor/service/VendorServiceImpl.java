package com.yongyonglee.vendor.domain.vendor.service;

import static com.yongyonglee.vendor.domain.vendor.model.QVendor.vendor;
import static java.util.Objects.isNull;

import com.querydsl.core.BooleanBuilder;
import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.dto.response.VendorResponseDto;
import com.yongyonglee.vendor.domain.vendor.exception.VendorException;
import com.yongyonglee.vendor.domain.vendor.message.ExceptionMessage;
import com.yongyonglee.vendor.domain.vendor.model.Vendor;
import com.yongyonglee.vendor.domain.vendor.model.constant.VendorCategory;
import com.yongyonglee.vendor.domain.vendor.repository.VendorRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    @Override
    public Page<VendorResponseDto> searchVendors(String vendorName, String vendorCategory,
            UUID hubId, Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        // isDeleted가 false인 경우만 조회
        builder.and(vendor.isDeleted.eq(false));

        if(StringUtils.hasText(vendorName)){
            builder.and(vendor.vendorName.containsIgnoreCase(vendorName));
        }

        if(StringUtils.hasText(vendorCategory)){
            VendorCategory category = VendorCategory.findByCategory(vendorCategory);
            builder.and(vendor.vendorCategory.eq(category));
        }

        if(!isNull(hubId)){
            builder.and(vendor.hubId.eq(hubId));
        }

        Page<Vendor> vendors = vendorRepository.findAll(builder, pageable);

        return vendors.map(VendorResponseDto::from);
    }

    public Vendor findById(UUID vendorId) {
        return vendorRepository.findByIdAndIsDeletedFalse(vendorId)
                .orElseThrow(() -> new VendorException(ExceptionMessage.VENDOR_NOT_FOUND));
    }
}
