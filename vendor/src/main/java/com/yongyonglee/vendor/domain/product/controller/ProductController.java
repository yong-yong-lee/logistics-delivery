package com.yongyonglee.vendor.domain.product.controller;

import static com.yongyonglee.vendor.domain.product.message.SuccessMessage.CREATE_PRODUCT_SUCCESS;
import static com.yongyonglee.vendor.domain.product.message.SuccessMessage.DELETE_PRODUCT_SUCCESS;
import static com.yongyonglee.vendor.domain.product.message.SuccessMessage.GET_PRODUCT_SUCCESS;
import static com.yongyonglee.vendor.domain.product.message.SuccessMessage.UPDATE_PRODUCT_SUCCESS;
import static com.yongyonglee.vendor.global.response.SuccessResponse.success;

import com.yongyonglee.vendor.domain.product.dto.request.CreateProductRequestDto;
import com.yongyonglee.vendor.domain.product.dto.request.UpdateProductQuantityRequestDto;
import com.yongyonglee.vendor.domain.product.service.ProductService;
import com.yongyonglee.vendor.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // TODO: 사용자 인증 및 인가(VENDOR_MANAGER, HUB_MANAGE, MASTER) 추가
    @Operation(summary = "상품 단건 조회", description = "상품을 단건 조회할 때 사용하는 API")
    @GetMapping("/{productId}")
    public ResponseEntity<? extends CommonResponse> getProduct(@PathVariable UUID productId) {
        return ResponseEntity.status(GET_PRODUCT_SUCCESS.getHttpStatus())
                .body(success(GET_PRODUCT_SUCCESS.getMessage(), productService.getProduct(productId)));
    }

    // TODO: 사용자 인증 및 인가(VENDOR_MANAGER, HUB_MANAGE, MASTER) 추가
    @Operation(summary = "상품 검색", description = "상품을 검색할 때 사용하는 API")
    @GetMapping("")
    public ResponseEntity<? extends CommonResponse> searchProducts(
            @RequestParam(required = false) UUID vendorId,
            @RequestParam(required = false) UUID hubId,
            @RequestParam(required = false) String productName,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(GET_PRODUCT_SUCCESS.getHttpStatus())
                .body(success(GET_PRODUCT_SUCCESS.getMessage(), productService.searchProducts(vendorId, hubId, productName, pageable)));
    }

    // TODO: 사용자 인증 및 인가(VENDOR_MANAGER, HUB_MANAGE, MASTER) 추가
    @Operation(summary = "상품 수량 변경", description = "상품 수량을 변경할 때 사용하는 API")
    @PatchMapping("/{productId}/quantity")
    public ResponseEntity<? extends CommonResponse> updateProductQuantity(
            @PathVariable UUID productId,
            @RequestBody UpdateProductQuantityRequestDto requestDto) {

        return ResponseEntity.status(UPDATE_PRODUCT_SUCCESS.getHttpStatus())
                .body(success(UPDATE_PRODUCT_SUCCESS.getMessage(), productService.updateProductQuantity(productId, requestDto)));
    }

    // TODO: 사용자 인증 및 인가(VENDOR_MANAGER, HUB_MANAGE, MASTER) 추가
    @Operation(summary = "상품 삭제", description = "상품을 삭제할 때 사용하는 API")
    @DeleteMapping("/{productId}")
    public ResponseEntity<? extends CommonResponse> deleteProduct(@PathVariable UUID productId) {

        productService.deleteProduct(productId);

        return ResponseEntity.status(DELETE_PRODUCT_SUCCESS.getHttpStatus())
                .body(success(DELETE_PRODUCT_SUCCESS.getMessage()));
    }
}
