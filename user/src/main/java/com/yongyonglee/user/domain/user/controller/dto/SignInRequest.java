package com.yongyonglee.user.domain.user.controller.dto;

import lombok.Getter;

@Getter
public class SignInRequest {
  private String email;
  private String password;

}
