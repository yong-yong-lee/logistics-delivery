package com.yongyonglee.vendor.domain.product.controller;

import static com.yongyonglee.vendor.domain.product.message.SuccessMessage.CREATE_PRODUCT_SUCCESS;
import static com.yongyonglee.vendor.global.response.SuccessResponse.success;

import com.yongyonglee.vendor.domain.product.dto.request.CreateProductRequestDto;
import com.yongyonglee.vendor.domain.product.service.ProductService;
import com.yongyonglee.vendor.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Product-Controller", description = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    // TODO: 사용자 인증 및 인가(VENDOR_MANAGER, HUB_MANAGE, MASTER) 추가
    @Operation(summary = "상품 등록", description = "상품을 등록할 때 사용하는 API")
    @PostMapping("")
    public ResponseEntity<? extends CommonResponse> createProduct(@RequestBody CreateProductRequestDto requestDto) {
        return ResponseEntity.status(CREATE_PRODUCT_SUCCESS.getHttpStatus())
                .body(success(CREATE_PRODUCT_SUCCESS.getMessage(), productService.createProduct(requestDto)));
    }
}
