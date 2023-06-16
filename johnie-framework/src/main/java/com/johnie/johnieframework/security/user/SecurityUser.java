package com.johnie.johnieframework.security.user;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户
 */
public final class SecurityUser {
    private SecurityUser() {
    }

    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        UserDetail userDetail;
        try {
            userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return new UserDetail();
        }

        return userDetail;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

}