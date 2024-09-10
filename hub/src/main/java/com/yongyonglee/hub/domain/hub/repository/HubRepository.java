package com.yongyonglee.hub.domain.hub.repository;

import com.yongyonglee.hub.domain.hub.model.Hub;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HubRepository extends JpaRepository<Hub, String> {

    Optional<Hub> findByIdAndIsDeletedFalse(String id);
}
