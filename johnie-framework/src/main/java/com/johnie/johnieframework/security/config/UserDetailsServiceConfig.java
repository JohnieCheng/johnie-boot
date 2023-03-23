package com.johnie.johnieframework.security.config;

import com.johnie.johnieframework.security.repository.SysAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfig {

  final SysAuthRepository authRepository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username ->
        authRepository
            .findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
  }
}
