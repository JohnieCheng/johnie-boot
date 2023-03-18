package com.johnie.johnieframework.security.config;

import com.johnie.johnieframework.repository.SysUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfig {

  final SysUserRepository userRepository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username ->
        userRepository
            .findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
  }
}
