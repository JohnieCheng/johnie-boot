package com.johnie.johniesystem.system.service.impl;

import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johniesystem.system.convert.SysUserConvert;
import com.johnie.johniesystem.system.entity.SysUser;
import com.johnie.johniesystem.system.service.SysUserDetailsService;
import com.johnie.johniesystem.system.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 用户 UserDetails 信息
 */
@Service
@AllArgsConstructor
public class SysUserDetailsServiceImpl implements SysUserDetailsService {
    private final SysUserConvert sysUserConvert;
    private final SysUserService sysUserService;

    @Override
    public UserDetails getUserDetails(SysUser user) {
        // 转换成UserDetail对象
        UserDetail userDetail = sysUserConvert.sourceToTarget(user);
// 账号不可用
//        if (user.getStatus() == UserStatusEnum.DISABLE.getValue()) {
//            userDetail.setEnabled(false);
//        }
//
//        // 数据权限范围
//        List<Long> dataScopeList = getDataScope(userDetail);
//        userDetail.setDataScopeList(dataScopeList);
//
//        // 用户权限列表
//        Set<String> authoritySet = sysMenuService.getUserAuthority(userDetail);
//        userDetail.setAuthoritySet(authoritySet);

        return userDetail;
    }

    @Override
    public UserDetail loadUserByUsername(String username) {
        return sysUserService.loadUserByUsername(username);
    }
}
