package com.johnie.johniesystem.system.service;


import com.johnie.johniesystem.system.vo.AuthRequest;
import com.johnie.johniesystem.system.vo.SysAuthResponse;
import com.johnie.johniesystem.system.vo.RegisterUserVo;

public interface SysAuthService {
  void register(RegisterUserVo request);

  SysAuthResponse login(AuthRequest request);

    void logout(String jwt);
}
