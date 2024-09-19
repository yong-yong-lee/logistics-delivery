package com.yongyonglee.user.global.util;

import com.yongyonglee.user.domain.user.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  public static final String BEARER_PREFIX = "Bearer ";
  public static final String ID = "id";
  public static final String USERNAME = "username";
  public static final String ROLE = "role";
  private final long TOKEN_TIME = 60 * 30 * 1000L;

  @Value("${jwt.secret-key}")
  private String secretKey;
  @Value("${jwt.audience}")
  private String audience;
  @Value("${spring.application.name}")
  private String issuer;
  private Key key;
  private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

  @PostConstruct
  public void init() {
    key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  public String createToken(Long id, Role role) {
    Date issuedDate = new Date();
    return BEARER_PREFIX +
        Jwts.builder()
            .issuer(issuer)
            .issuedAt(issuedDate)
            .claim(ID, id)
            .claim(ROLE, role)
            .expiration(new Date(issuedDate.getTime() + TOKEN_TIME))
            .signWith(key)
            .compact();
  }

}
