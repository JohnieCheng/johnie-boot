package com.johnie.johnieframework.security.exception;

import com.johnie.johnieframework.common.exception.ErrorCode;
import com.johnie.johnieframework.common.utils.HttpContextUtils;
import com.johnie.johnieframework.common.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 匿名用户(token不存在、错误)，异常处理器
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());

        response.getWriter()
                .print(JsonUtils.toJsonString(
                                ResponseEntity.status(ErrorCode.UNAUTHORIZED.getCode()).build()
                        )
                );
    }
}