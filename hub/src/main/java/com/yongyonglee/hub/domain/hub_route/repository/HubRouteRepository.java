package com.yongyonglee.hub.domain.hub_route.repository;

import com.yongyonglee.hub.domain.hub_route.model.HubRoute;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HubRouteRepository extends JpaRepository<HubRoute, UUID> {

    Optional<HubRoute> findByIdAndIsDeletedFalse(UUID hubRouteId);

    @Query("SELECT hr FROM p_hub_route hr " +
            "JOIN FETCH hr.departureHub d " +
            "JOIN FETCH hr.arrivalHub a " +
            "WHERE d.isDeleted = false AND a.isDeleted = false AND hr.isDeleted = false")
    List<HubRoute> findAllActiveHubRoutes();
}
