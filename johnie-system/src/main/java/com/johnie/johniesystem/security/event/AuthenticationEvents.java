package com.johnie.johniesystem.security.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 认证事件处理
 */
@Component
@AllArgsConstructor
public class AuthenticationEvents {
//    private final SysLogLoginService sysLogLoginService;
//
//    @EventListener
//    public void onSuccess(AuthenticationSuccessEvent event) {
//        // 用户信息
//        User user = (User) event.getAuthentication().getPrincipal();
//
//        // 保存登录日志
//        sysLogLoginService.save(user.getUsername(), Constant.SUCCESS, LoginOperationEnum.LOGIN_SUCCESS.getValue());
//    }
//
//    @EventListener
//    public void onFailure(AbstractAuthenticationFailureEvent event) {
//        // 用户名
//        String username = (String) event.getAuthentication().getPrincipal();
//
//        // 保存登录日志
//        sysLogLoginService.save(username, Constant.FAIL, LoginOperationEnum.ACCOUNT_FAIL.getValue());
//    }

}