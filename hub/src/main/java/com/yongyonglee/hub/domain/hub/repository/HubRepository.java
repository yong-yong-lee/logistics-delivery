package com.yongyonglee.hub.domain.hub.repository;

import com.yongyonglee.hub.domain.hub.model.Hub;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HubRepository extends JpaRepository<Hub, String>, QuerydslPredicateExecutor<Hub> {

    Optional<Hub> findByIdAndIsDeletedFalse(String id);

    Page<Hub> findAllByIsDeletedFalse(Pageable pageable);
}
