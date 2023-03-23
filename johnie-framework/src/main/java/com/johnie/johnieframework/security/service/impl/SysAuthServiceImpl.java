package com.johnie.johnieframework.security.service.impl;

import com.johnie.johniecommon.vo.AuthRequest;
import com.johnie.johniecommon.vo.AuthResponse;
import com.johnie.johniecommon.vo.RegisterUserVo;
import com.johnie.johnieframework.entity.system.SysUser;
import com.johnie.johnieframework.security.repository.SysAuthRepository;
import com.johnie.johnieframework.security.service.JwtService;
import com.johnie.johnieframework.security.service.SysAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {
  private final SysAuthRepository sysAuthRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SysUser sysUser = sysAuthRepository.findByEmail(request.getEmail()).orElseThrow();
    String jwt = jwtService.generateToken(sysUser);
    return AuthResponse.builder().accessToken(jwt).build();
  }

  @Override
  public AuthResponse register(RegisterUserVo request) {
    SysUser userDetail =
        SysUser.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();
    sysAuthRepository.save(userDetail);
    String jwtToken = jwtService.generateToken(userDetail);
    return AuthResponse.builder().accessToken(jwtToken).build();
  }
}
