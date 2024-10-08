package com.yongyonglee.user.domain.user.repository;

import com.yongyonglee.user.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByNickname(String nickname);

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String email);

}
