package com.yongyonglee.hub.domain.hub_route.service;

import com.yongyonglee.hub.domain.hub_route.model.HubRoute;
import com.yongyonglee.hub.domain.hub_route.repository.HubRouteRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HubRouteHelper {

    private final HubRouteRepository hubRouteRepository;

    public void deleteHubRoutesByHubId(UUID hubId, String userName) {
        List<HubRoute> hubRoutes = hubRouteRepository.findAllRelatedHubId(hubId);

        for (HubRoute hubRoute : hubRoutes) {
            hubRoute.deleteHubRoute(userName);
        }
    }
}
