package com.johnie.johniesystem.system.service.impl;


import com.johnie.johnieframework.common.exception.ServerException;
import com.johnie.johnieframework.security.cache.TokenStoreCache;
import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johnieframework.security.service.JwtService;
import com.johnie.johniesystem.system.service.SysAuthService;
import com.johnie.johniesystem.system.service.SysUserDetailsService;
import com.johnie.johniesystem.system.service.SysUserService;
import com.johnie.johniesystem.system.vo.AuthRequest;
import com.johnie.johniesystem.system.vo.SysAuthResponse;
import com.johnie.johniesystem.system.vo.RegisterUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenStoreCache tokenStoreCache;
    private final SysUserService sysUserService;
    private final SysUserDetailsService sysUserDetailsService;

    @Override
    public SysAuthResponse login(AuthRequest request) {
//        UserDetail userDetail = sysUserDetailsService.loadUserByUsername(request.getUsername());
        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ServerException("用户名或密码错误");
        }
        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();
        // 生成 accessToken
        String jwt = jwtService.generateToken(user);
        // 保存用户信息到缓存
        tokenStoreCache.saveUser(jwt, user);
        // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return SysAuthResponse.builder().accessToken(jwt).build();
    }

    @Override
    public void register(RegisterUserVo request) {
        UserDetail userDetail = UserDetail.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .departmentNo(request.getDepartmentNo())
                .roleNo(request.getRoleNo())
                .build();
        sysUserService.register(userDetail);
    }
}
