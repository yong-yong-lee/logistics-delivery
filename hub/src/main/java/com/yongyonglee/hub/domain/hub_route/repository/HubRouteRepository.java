package com.yongyonglee.hub.domain.hub_route.repository;

import com.yongyonglee.hub.domain.hub_route.model.HubRoute;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HubRouteRepository extends JpaRepository<HubRoute, UUID> {

    Optional<HubRoute> findByIdAndIsDeletedFalse(UUID hubRouteId);
}
