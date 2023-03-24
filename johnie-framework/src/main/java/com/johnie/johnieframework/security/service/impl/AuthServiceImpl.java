package com.johnie.johnieframework.security.service.impl;

import com.johnie.johniecommon.exception.ServerException;import com.johnie.johniecommon.vo.AuthRequest;
import com.johnie.johniecommon.vo.AuthResponse;
import com.johnie.johniecommon.vo.RegisterUserVo;
import com.johnie.johnieframework.entity.system.User;
import com.johnie.johnieframework.security.repository.AuthRepository;
import com.johnie.johnieframework.security.service.AuthService;
import com.johnie.johnieframework.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
  private final AuthRepository authRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = authRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ServerException(""));
    String jwt = jwtService.generateToken(user);
    return AuthResponse.builder().accessToken(jwt).build();
  }

  @Override
  public AuthResponse register(RegisterUserVo request) {
    User userDetail =
        User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();
    authRepository.save(userDetail);
    String jwtToken = jwtService.generateToken(userDetail);
    return AuthResponse.builder().accessToken(jwtToken).build();
  }
}
