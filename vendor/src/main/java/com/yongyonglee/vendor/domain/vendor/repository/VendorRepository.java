package com.yongyonglee.vendor.domain.vendor.repository;

import com.yongyonglee.vendor.domain.vendor.model.Vendor;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {

    Optional<Vendor> findByIdAndIsDeletedFalse(UUID vendorId);
}
