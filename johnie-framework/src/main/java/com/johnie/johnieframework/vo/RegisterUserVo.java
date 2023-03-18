package com.johnie.johnieframework.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserVo {
  private String email;
  private String password;
}
