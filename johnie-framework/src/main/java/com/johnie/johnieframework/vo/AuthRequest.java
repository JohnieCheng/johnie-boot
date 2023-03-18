package com.johnie.johnieframework.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AuthRequest {
  private String email;
  private String password;
}
