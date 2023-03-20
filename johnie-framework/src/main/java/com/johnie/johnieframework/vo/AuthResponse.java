package com.johnie.johnieframework.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
  private String accessToken;
  private SimpleUser user;
}
