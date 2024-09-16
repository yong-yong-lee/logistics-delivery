package com.yongyonglee.vendor.domain.product.repository;

import com.yongyonglee.vendor.domain.product.model.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
