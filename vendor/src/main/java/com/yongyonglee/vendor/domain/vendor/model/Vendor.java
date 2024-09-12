package com.yongyonglee.vendor.domain.vendor.model;

import com.yongyonglee.vendor.domain.vendor.model.constant.VendorCategory;
import com.yongyonglee.vendor.global.entity.TimeBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "p_vendor")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vendor extends TimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT")
    private Long userId;

    @Column(name = "hub_id", nullable = false)
    private UUID hubId;

    @Column(name = "vendor_name", nullable = false, columnDefinition = "VARCHAR(100)")
    private String vendorName;

    @Column(name = "vendor_category", nullable = false, columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private VendorCategory vendorCategory;

    @Column(name = "vendor_address", nullable = false, columnDefinition = "VARCHAR(100)")
    private String vendorAddress;

}