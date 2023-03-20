package com.johnie.johnieframework.security.service.impl;

import com.johnie.johnieframework.entity.SysUser;
import com.johnie.johnieframework.repository.SysUserRepository;
import com.johnie.johnieframework.security.service.JwtService;
import com.johnie.johnieframework.security.service.SysAuthService;
import com.johnie.johnieframework.vo.AuthRequest;
import com.johnie.johnieframework.vo.AuthResponse;
import com.johnie.johnieframework.vo.RegisterUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {
  private final SysUserRepository sysUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    SysUser sysUser = sysUserRepository.findByEmail(request.getEmail()).orElseThrow();
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
    sysUserRepository.save(userDetail);
    String jwtToken = jwtService.generateToken(userDetail);
    return AuthResponse.builder().accessToken(jwtToken).build();
  }
}
