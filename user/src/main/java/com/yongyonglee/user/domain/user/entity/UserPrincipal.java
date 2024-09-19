package com.yongyonglee.user.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPrincipal {

  private final Long id;
  private final String role;

  public static UserPrincipal of(Long id,String role) {
    return new UserPrincipal(id, role);
  }
}
