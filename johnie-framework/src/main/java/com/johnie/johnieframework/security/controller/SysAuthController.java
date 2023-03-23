package com.johnie.johnieframework.security.controller;

import com.johnie.johnieframework.security.service.SysAuthService;
import com.johnie.johniecommon.vo.AuthRequest;
import com.johnie.johniecommon.vo.AuthResponse;
import com.johnie.johniecommon.vo.RegisterUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SysAuthController {

  private final SysAuthService service;

  @PostMapping("register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterUserVo request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("authenticate")
  public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
