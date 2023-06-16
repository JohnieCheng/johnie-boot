package com.johnie.johniesystem.system.service.impl;


import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johnieframework.security.service.JwtService;
import com.johnie.johniesystem.system.service.SysAuthService;
import com.johnie.johniesystem.system.vo.AuthRequest;
import com.johnie.johniesystem.system.vo.SysAuthResponse;
import com.johnie.johniesystem.system.vo.RegisterUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public SysAuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String jwt = jwtService.generateToken(userDetails);
        return SysAuthResponse.builder().accessToken(jwt).build();
    }

    @Override
    public SysAuthResponse register(RegisterUserVo request) {
        UserDetails userDetails = UserDetail.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
//        authRepository.save(userDetail);
        String jwtToken = jwtService.generateToken(userDetails);
        return SysAuthResponse.builder().accessToken(jwtToken).build();
    }
}
