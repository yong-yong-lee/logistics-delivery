package com.yongyonglee.user.global.filter;

import com.yongyonglee.user.domain.user.entity.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String id = request.getHeader("User-Id");
    String role = request.getHeader("User-Role");

    if (id != null && role != null) {
      UserPrincipal userPrincipal = UserPrincipal.of(Long.valueOf(id), role);

      GrantedAuthority authority = new SimpleGrantedAuthority(role);
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          userPrincipal, null, List.of(authority)
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getRequestURI();
    // 특정 경로를 무시하도록 설정
    return path.startsWith("/api/v1/auth/sign-in") || path.startsWith("/api/v1/auth/sign-up");
  }
}
