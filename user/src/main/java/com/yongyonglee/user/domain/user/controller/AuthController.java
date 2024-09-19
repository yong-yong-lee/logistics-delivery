package com.yongyonglee.user.domain.user.controller;

import com.yongyonglee.user.domain.user.controller.dto.SignUpRequest;
import com.yongyonglee.user.domain.user.service.AuthService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/sign-up")
  ResponseEntity<Void> signUp(@RequestBody SignUpRequest signUpRequest){
    Long userId = authService.createUser(signUpRequest);
    URI userUri = UriComponentsBuilder.fromUriString("/user/{userId}")
        .buildAndExpand(userId)
        .toUri();
    return ResponseEntity.created(userUri).build();
  }



}
