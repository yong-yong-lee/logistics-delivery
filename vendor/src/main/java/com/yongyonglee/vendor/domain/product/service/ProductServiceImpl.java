package com.yongyonglee.vendor.domain.product.service;

import com.yongyonglee.vendor.domain.product.dto.request.CreateProductRequestDto;
import com.yongyonglee.vendor.domain.product.dto.response.ProductResponseDto;
import com.yongyonglee.vendor.domain.product.exception.ProductException;
import com.yongyonglee.vendor.domain.product.message.ExceptionMessage;
import com.yongyonglee.vendor.domain.product.model.Product;
import com.yongyonglee.vendor.domain.product.repository.ProductRepository;
import com.yongyonglee.vendor.domain.vendor.model.Vendor;
import com.yongyonglee.vendor.domain.vendor.service.VendorService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final VendorService vendorService;

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {

        Vendor vendor = vendorService.findById(requestDto.vendorId());

        Product product = requestDto.toEntity(vendor);
        productRepository.save(product);

        return ProductResponseDto.from(product);
    }

    @Override
    public ProductResponseDto getProduct(UUID productId) {

        Product product = findById(productId);

        return ProductResponseDto.from(product);
    }

    @Override
    public Product findById(UUID productId) {

        return productRepository.findByIdAndIsDeletedFalse(productId)
                .orElseThrow(() -> new ProductException(ExceptionMessage.PRODUCT_NOT_FOUND));
    }
}
