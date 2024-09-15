package com.yongyonglee.vendor.domain.client;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service", url = "http://localhost:19095/api/v1/hubs")
@Qualifier("hub-service")
public interface HubClient {

    @GetMapping("/{hubId}/exists")
    Boolean isExistHub(@PathVariable("hubId") UUID hubId);
}
