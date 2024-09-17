package com.yongyonglee.vendor.domain.product.repository;

import com.querydsl.core.BooleanBuilder;
import com.yongyonglee.vendor.domain.product.model.Product;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, QuerydslPredicateExecutor<Product> {

    Optional<Product> findByIdAndIsDeletedFalse(UUID productId);
}
