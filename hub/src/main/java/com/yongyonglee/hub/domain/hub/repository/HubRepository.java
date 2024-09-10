package com.yongyonglee.hub.domain.hub.repository;

import com.yongyonglee.hub.domain.hub.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HubRepository extends JpaRepository<Hub, String> {

}
