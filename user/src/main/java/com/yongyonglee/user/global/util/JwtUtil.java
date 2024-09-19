package com.yongyonglee.user.global.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.yongyonglee.user.domain.user.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  public static final String BEARER_PREFIX = "Bearer ";
  public static final String ID = "id";
  public static final String ROLE = "role";
  private final long TOKEN_TIME = 60 * 30 * 1000L;

  @Value("${jwt.secret-key}")
  private String secretKey;
  @Value("${jwt.audience}")
  private String audience;
  @Value("${spring.application.name}")
  private String issuer;
  private Key key;

  @PostConstruct
  public void init() {
    key = generateSigningKey(secretKey);
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
