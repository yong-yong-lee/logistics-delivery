package com.yongyonglee.user.domain.user.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.yongyonglee.user.domain.user.controller.dto.SignInRequest;
import com.yongyonglee.user.domain.user.controller.dto.SignUpRequest;
import com.yongyonglee.user.domain.user.entity.UserPrincipal;
import com.yongyonglee.user.domain.user.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
  @PostMapping("/sign-in")
    ResponseEntity<Void> signIn(@RequestBody SignInRequest signInRequest, HttpServletResponse res){
      String accessToken = authService.authentication(signInRequest);
      res.setHeader(AUTHORIZATION, accessToken);
      return ResponseEntity.ok().build();
  }

  @GetMapping("/test")
  public ResponseEntity<String> test(@AuthenticationPrincipal UserPrincipal userPrincipal) {
    System.out.println("userPrincipal.getId() = " + userPrincipal.getId());
    System.out.println("userPrincipal.getRole() = " + userPrincipal.getRole());
    return ResponseEntity.ok("Role: " + userPrincipal.getRole());
  }



}
