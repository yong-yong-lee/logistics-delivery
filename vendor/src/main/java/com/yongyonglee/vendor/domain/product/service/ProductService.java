package com.yongyonglee.vendor.domain.product.service;

import com.yongyonglee.vendor.domain.product.dto.request.CreateProductRequestDto;
import com.yongyonglee.vendor.domain.product.dto.response.ProductResponseDto;

public interface ProductService {

    ProductResponseDto createProduct(CreateProductRequestDto requestDto);
}
