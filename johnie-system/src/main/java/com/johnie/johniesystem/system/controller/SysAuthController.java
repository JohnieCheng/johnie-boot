package com.johnie.johniesystem.system.controller;

import com.johnie.johnieframework.common.exception.ErrorCode;
import com.johnie.johniesystem.system.service.SysAuthService;
import com.johnie.johniesystem.system.vo.AuthRequest;
import com.johnie.johniesystem.system.vo.SysAuthResponse;
import com.johnie.johniesystem.system.vo.RegisterUserVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.johnie.johnieframework.security.utils.JwtUtil.getAccessToken;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SysAuthController {

    private final SysAuthService service;

    @PostMapping("register")
    public ResponseEntity<Object> register(@RequestBody RegisterUserVo request) {
        service.register(request);
        return ResponseEntity.ok().body(ErrorCode.REGISTER_SUCCESS.getMsg());
    }

    @PostMapping("login")
    public ResponseEntity<SysAuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.login(request));
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String accessToken = getAccessToken(request);
        service.logout(accessToken);
        return ResponseEntity.ok().body(ErrorCode.LOGOUT_SUCCESS.getMsg());
    }
}
