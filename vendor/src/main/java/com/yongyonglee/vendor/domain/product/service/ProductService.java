package com.yongyonglee.vendor.domain.product.service;

import com.yongyonglee.vendor.domain.product.dto.request.CreateProductRequestDto;
import com.yongyonglee.vendor.domain.product.dto.request.UpdateProductQuantityRequestDto;
import com.yongyonglee.vendor.domain.product.dto.response.ProductResponseDto;
import com.yongyonglee.vendor.domain.product.model.Product;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponseDto createProduct(CreateProductRequestDto requestDto);

    ProductResponseDto getProduct(UUID productId);

    Page<ProductResponseDto> searchProducts(UUID vendorId, UUID hubId, String productName, Pageable pageable);

    ProductResponseDto updateProductQuantity(UUID productId, UpdateProductQuantityRequestDto requestDto);

    void deleteProduct(UUID productId);

    Product findById(UUID productId);
}
