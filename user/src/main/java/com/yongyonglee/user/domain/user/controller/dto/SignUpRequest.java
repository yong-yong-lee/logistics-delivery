package com.yongyonglee.user.domain.user.controller.dto;

import com.yongyonglee.user.domain.user.entity.Role;
import com.yongyonglee.user.domain.user.entity.User;
import lombok.Getter;

@Getter
public class SignUpRequest {
  private String username;
  private String nickname;
  private String email;
  private String password;
  private String hubId;
  private String slackId;
  private Role role;

  public User toEntity(String password) {
    return User.of(username,nickname,email,password,hubId,slackId,role);
  }

}
