package com.johnie.johnieframework.security.service;

import com.johnie.johnieframework.vo.AuthRequest;
import com.johnie.johnieframework.vo.AuthResponse;
import com.johnie.johnieframework.vo.RegisterUserVo;

public interface SysAuthService {
  AuthResponse register(RegisterUserVo request);

  AuthResponse authenticate(AuthRequest request);
}
