package com.yongyonglee.order.domain.route;


import com.yongyonglee.order.domain.route.dto.HubRouteResponseDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HubRouteResponseWrapper {
    private String message;
    private List<HubRouteResponseDto> data;
}
