package com.yongyonglee.gateway.filter;

import static java.nio.charset.StandardCharsets.UTF_8;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;
import jakarta.security.auth.message.AuthException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtAuthenticationFilter implements GlobalFilter {

  @Value("${jwt.secret-key}")
  private String secretKey;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String path = exchange.getRequest().getURI().getPath();

    if (path.equals("/api/v1/auth/sign-up") || path.equals("/api/v1/auth/sign-in"))
         {
           System.out.println("로그인 ,회원가입은 통과");
      return chain.filter(exchange);
    }

    String token = extractToken(exchange);
    Claims claims = validateToken(token);

    if (token == null || claims.isEmpty()) {
      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      // 응답을 완료하여 요청을 더 이상 처리하지 않도록
      return exchange.getResponse().setComplete();
    }
    String id = (String) claims.get("id");
    String role = (String) claims.get("role");

    exchange.getRequest().mutate()
        .header("User-Id", id)
        .header("User-Role", role)
        .build();

    return chain.filter(exchange);
  }

  // JWT 토큰 추출
  private String extractToken(ServerWebExchange exchange) {
    String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.substring(7);
    }
    return null;
  }

  // 토큰 유효성 검사
  private Claims validateToken(String token) {
    SecretKey key = generateSigningKey(secretKey);
    try {
      return Jwts.parser()
          .verifyWith(key)
          .build()
          .parseSignedClaims(token)
          .getPayload();
    }catch (WeakKeyException | NullPointerException weakKeyException) {
      throw new IllegalArgumentException("잘못된 Key");
    } catch (ExpiredJwtException expiredJwtException) {
      throw new IllegalArgumentException("유효기간이 지난 토큰입니다.");
    } catch (MalformedJwtException malformedJwtException) {
      throw new IllegalArgumentException("jwt 형식이 아닙니다. ");
    } catch (Exception unexpectedException) {
      throw new RuntimeException(unexpectedException);
    }
  }

  public  SecretKey generateSigningKey(String plainSecretKey) {
    byte[] base64SecretKey = encodeToBase64(plainSecretKey);
    return Keys.hmacShaKeyFor(base64SecretKey);
  }

  private  byte[] encodeToBase64(String base64SecretKey) {
    return Base64.getEncoder()
        .withoutPadding()
        .encode(base64SecretKey.getBytes(UTF_8));
  }




}
