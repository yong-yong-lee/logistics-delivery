package com.yongyonglee.user.domain.user.service;

import com.yongyonglee.user.domain.user.controller.dto.SignUpRequest;
import com.yongyonglee.user.domain.user.entity.User;
import com.yongyonglee.user.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  private static final String DUPLICATED_EMAIL_ERROR_MESSAGE = "이미 존재하는 이메일입니다.";
  private static final String DUPLICATED_NICKNAME_ERROR_MESSAGE = "이미 존재하는 닉네임입니다.";
  public static final String INVALID_AUTHENTICATION_ERROR_MESSAGE = "사용자 정보가 올바르지 않습니다.";


  public Long createUser(SignUpRequest signUpRequest) {
    validate(signUpRequest);
    String password = passwordEncoder.encode(signUpRequest.getPassword());
    User user = signUpRequest.toEntity(password);
    User savedUser = userRepository.save(user);
    return savedUser.getId();
  }
  private void validate(SignUpRequest signUpRequest) {
    validateDuplicatedEmail(signUpRequest.getEmail());
    validateDuplicatedNickname(signUpRequest.getNickname());
  }

  private void validateDuplicatedNickname(String nickname) {
    boolean exits = userRepository.existsByNickname(nickname);
    if(exits){
      throw new IllegalArgumentException(DUPLICATED_NICKNAME_ERROR_MESSAGE);
    }
  }

  private void validateDuplicatedEmail(String email) {
    boolean exits = userRepository.existsByEmail(email);
    if(exits){
      throw new IllegalArgumentException(DUPLICATED_EMAIL_ERROR_MESSAGE);
    }
  }




}
