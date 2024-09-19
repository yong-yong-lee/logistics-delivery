package com.yongyonglee.user.global.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.yongyonglee.user.global.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final AuthenticationFilter authenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
        .sessionManagement(session -> session
            .sessionCreationPolicy(STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/v1/auth/**").permitAll() // 인증 없이 접근할 수 있는 경로
            .anyRequest().authenticated()
        )
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

}
