package com.yongyonglee.vendor.domain.product.service;

import static com.yongyonglee.vendor.domain.product.model.QProduct.product;
import static java.util.Objects.isNull;

import com.querydsl.core.BooleanBuilder;
import com.yongyonglee.vendor.domain.product.dto.request.CreateProductRequestDto;
import com.yongyonglee.vendor.domain.product.dto.request.UpdateProductQuantityRequestDto;
import com.yongyonglee.vendor.domain.product.dto.response.ProductResponseDto;
import com.yongyonglee.vendor.domain.product.exception.ProductException;
import com.yongyonglee.vendor.domain.product.message.ExceptionMessage;
import com.yongyonglee.vendor.domain.product.model.Product;
import com.yongyonglee.vendor.domain.product.repository.ProductRepository;
import com.yongyonglee.vendor.domain.vendor.model.Vendor;
import com.yongyonglee.vendor.domain.vendor.service.VendorService;
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
public class ProductServiceImpl implements ProductService {

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
    public Page<ProductResponseDto> searchProducts(UUID vendorId, UUID hubId, String productName,
            Pageable pageable) {

        BooleanBuilder builder = new BooleanBuilder();

        // isDeleted가 false인 경우만 조회
        builder.and(product.isDeleted.eq(false));

        if (!isNull(vendorId)) {
            builder.and(product.vendor.id.eq(vendorId));
        }

        if (!isNull(hubId)) {
            builder.and(product.hubId.eq(hubId));
        }

        if (StringUtils.hasText(productName)) {
            builder.and(product.productName.containsIgnoreCase(productName));
        }

        Page<Product> products = productRepository.findAll(builder, pageable);

        return products.map(ProductResponseDto::from);
    }

    @Override
    public ProductResponseDto updateProductQuantity(UUID productId,
            UpdateProductQuantityRequestDto requestDto) {

        Product product = findById(productId);

        product.updateQuantity(requestDto.quantity());

        return ProductResponseDto.from(product);
    }

    @Override
    public Product findById(UUID productId) {

        return productRepository.findByIdAndIsDeletedFalse(productId)
                .orElseThrow(() -> new ProductException(ExceptionMessage.PRODUCT_NOT_FOUND));
    }
}
