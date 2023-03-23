package com.johnie.johnieframework.security.service;

import com.johnie.johniecommon.vo.AuthRequest;
import com.johnie.johniecommon.vo.AuthResponse;
import com.johnie.johniecommon.vo.RegisterUserVo;

public interface SysAuthService {
  AuthResponse register(RegisterUserVo request);

  AuthResponse authenticate(AuthRequest request);
}
