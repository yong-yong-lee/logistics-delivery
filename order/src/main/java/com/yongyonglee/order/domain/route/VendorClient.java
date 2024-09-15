package com.yongyonglee.order.domain.route;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vendor-service")
public interface VendorClient {

    @GetMapping("/api/v1/vendors/{vendorId}")
    ResponseEntity<VendorResponseDto> getVendor(@PathVariable("vendorId") UUID vendorId);
}