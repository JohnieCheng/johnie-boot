package com.johnie.johniesystem.system.service;

import com.johnie.johniesystem.system.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface SysUserDetailsService {
    /**
     * 获取 UserDetails 对象
     */
    UserDetails getUserDetails(SysUser sysUser);
}
