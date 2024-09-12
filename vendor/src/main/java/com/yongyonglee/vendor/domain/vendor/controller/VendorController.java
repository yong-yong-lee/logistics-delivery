package com.yongyonglee.vendor.domain.vendor.controller;

import static com.yongyonglee.vendor.domain.vendor.message.SuccessMessage.CREATE_VENDOR_SUCCESS;
import static com.yongyonglee.vendor.global.response.SuccessResponse.success;

import com.yongyonglee.vendor.domain.vendor.dto.request.CreateVendorRequestDto;
import com.yongyonglee.vendor.domain.vendor.service.VendorService;
import com.yongyonglee.vendor.global.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
