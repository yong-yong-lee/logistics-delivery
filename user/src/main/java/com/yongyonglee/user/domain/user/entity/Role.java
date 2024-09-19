package com.yongyonglee.user.domain.user.entity;

public enum Role {
  MASTER("관리자"), MANAGER("허브 관리자"), HUB_DELIVER("허브 이동 담당자"), VENDOR_DELIVER("업체 배송 담당자"), VENDOR_MANAGER("업체 관리자");

  private String description;

  Role(String description) {
    this.description = description;
  }
}
