package com.johnie.johniesystem.system.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SysAuthResponse {
  private String accessToken;
  private UserVo user;
}
