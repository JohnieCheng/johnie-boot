package com.johnie.johniesystem.system.controller;

import com.johnie.johniesystem.system.service.SysAuthService;
import com.johnie.johniesystem.system.vo.AuthRequest;
import com.johnie.johniesystem.system.vo.SysAuthResponse;
import com.johnie.johniesystem.system.vo.RegisterUserVo;
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
  public ResponseEntity<SysAuthResponse> register(@RequestBody RegisterUserVo request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("authenticate")
  public ResponseEntity<SysAuthResponse> authenticate(@RequestBody AuthRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }
}
