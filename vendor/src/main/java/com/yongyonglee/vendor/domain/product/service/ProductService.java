package com.yongyonglee.vendor.domain.product.service;

import com.yongyonglee.vendor.domain.product.dto.request.CreateProductRequestDto;
import com.yongyonglee.vendor.domain.product.dto.response.ProductResponseDto;
import com.yongyonglee.vendor.domain.product.model.Product;
import java.util.UUID;

public interface ProductService {

    ProductResponseDto createProduct(CreateProductRequestDto requestDto);

    ProductResponseDto getProduct(UUID productId);

    Product findById(UUID productId);
}
