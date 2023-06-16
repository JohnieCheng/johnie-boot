package com.johnie.johniesystem.security.service;

import com.johnie.johniesystem.system.entity.SysUser;
import com.johnie.johniesystem.system.repository.SysAuthRepository;
import com.johnie.johniesystem.system.service.SysUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SysUserDetailsService sysUserDetailsService;
    private final SysAuthRepository sysAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysAuthRepository.findByEmail(username).
                orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return sysUserDetailsService.getUserDetails(sysUser);
    }
}
