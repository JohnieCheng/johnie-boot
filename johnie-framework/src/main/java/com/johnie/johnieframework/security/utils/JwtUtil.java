package com.johnie.johnieframework.security.utils;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class JwtUtil {
    /**
     * 获取 AccessToken
     */
    public static String getAccessToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String jwt = "";
        if (StringUtils.hasLength(authHeader) && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
        }
        return jwt;
    }
}
