package com.yongyonglee.user.domain.user.entity;

import com.yongyonglee.user.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String username;

  @Column(length = 100)
  private String nickname;

  @Column(nullable = false, unique = true, length = 255)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  @Column(length = 255)
  private String slackId;

  @Column(nullable = false)
  private UUID hubId;  // 허브 ID, Foreign Key

  private User(String username, String nickname, String email, String password,String hubId,String slackId, Role role) {
    this.username = username;
    this.nickname = nickname;
    this.email = email;
    this.password = password;
    this.slackId = slackId;
    this.role = role;
    this.hubId = UUID.fromString(hubId);
  }


  public static User of(String username, String nickname, String email, String password, String hubId, String slackId ,Role role) {
    return new User(username, nickname, email, password, hubId, slackId,role);
  }

}
