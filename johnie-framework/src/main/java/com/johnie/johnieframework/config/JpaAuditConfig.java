package com.johnie.johnieframework.config;

import java.util.Optional;

import com.johnie.johnieframework.security.user.UserDetail;
import org.hibernate.cfg.annotations.Nullability;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // 通过springSecurity获取当前用户
        Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).orElse(new UsernamePasswordAuthenticationToken(new UserDetail(), null));
        UserDetail userDetail = (UserDetail) Optional.ofNullable(authentication.getPrincipal()).orElse(new UserDetail());
        return () -> Optional.ofNullable(userDetail.getUsername());
    }
}
