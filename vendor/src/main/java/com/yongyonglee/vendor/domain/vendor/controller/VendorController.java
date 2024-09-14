package com.yongyonglee.vendor.domain.vendor.controller;

import static com.yongyonglee.vendor.domain.vendor.message.SuccessMessage.CREATE_VENDOR_SUCCESS;
import static com.yongyonglee.vendor.domain.vendor.message.SuccessMessage.DELETE_VENDOR_SUCCESS;
import static com.yongyonglee.vendor.domain.vendor.message.SuccessMessage.GET_VENDOR_SUCCESS;
import static com.yongyonglee.vendor.global.response.SuccessResponse.success;

import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.service.VendorService;
import com.yongyonglee.vendor.global.aop.page.PageSizeLimit;
import com.yongyonglee.vendor.global.response.CommonResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    /** 업체 생성 api */
    // TODO: 사용자 인증 및 인가(MASTER) 추가
    @PostMapping("")
    public ResponseEntity<? extends CommonResponse> createVendor(@RequestBody CreateVendorRequestDto requestDto) {
        return ResponseEntity.status(CREATE_VENDOR_SUCCESS.getHttpStatus())
                .body(success(CREATE_VENDOR_SUCCESS.getMessage(), vendorService.createVendor(requestDto)));
    }

    /** 업체 단건 조회 api */
    // TODO: 사용자 인증 추가
    @GetMapping("/{vendorId}")
    public ResponseEntity<? extends CommonResponse> getVendor(@PathVariable UUID vendorId) {
        return ResponseEntity.status(GET_VENDOR_SUCCESS.getHttpStatus())
                .body(success(GET_VENDOR_SUCCESS.getMessage(), vendorService.getVendor(vendorId)));
    }

    /** 업체 검색 api */
    // TODO: 사용자 인증 추가
    @GetMapping("")
    @PageSizeLimit
    public ResponseEntity<? extends CommonResponse> searchVendors(
            @RequestParam(required = false) String vendorName,
            @RequestParam(required = false) String vendorCategory,
            @RequestParam(required = false) UUID hubId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.status(GET_VENDOR_SUCCESS.getHttpStatus())
                .body(success(GET_VENDOR_SUCCESS.getMessage(), vendorService.searchVendors(vendorName, vendorCategory, hubId, pageable)));
    }

    /** 업체 삭제 api */
    // TODO: 사용자 인증 및 인가(VENDOR_MANAGER, HUB_MANAGER, MASTER) 추가
    @DeleteMapping("/{vendorId}")
    public ResponseEntity<? extends CommonResponse> deleteVendor(@PathVariable UUID vendorId) {

        vendorService.deleteVendor(vendorId);

        return ResponseEntity.status(DELETE_VENDOR_SUCCESS.getHttpStatus())
                .body(success(DELETE_VENDOR_SUCCESS.getMessage()));
    }
}
