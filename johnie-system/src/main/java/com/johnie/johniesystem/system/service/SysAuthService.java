package com.johnie.johniesystem.system.service;


import com.johnie.johniesystem.system.vo.AuthRequest;
import com.johnie.johniesystem.system.vo.SysAuthResponse;
import com.johnie.johniesystem.system.vo.RegisterUserVo;

public interface SysAuthService {
  SysAuthResponse register(RegisterUserVo request);

  SysAuthResponse authenticate(AuthRequest request);
}
