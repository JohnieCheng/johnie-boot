package com.johnie.johniesystem.system.convert;


import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johniesystem.system.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class SysUserConvertImplTest {
    public SysUserConvertImplTest() {
    }

    @InjectMocks
    private SysUserConvert sysUserConvert = new SysUserConvertImpl();


    @Test
    public void sourceToTarget() {
//        given
        SysUser sysUser = new SysUser();
        sysUser.setUsername("name");
        sysUser.setPassword("123");
//        then
        UserDetail userDetail = sysUserConvert.sourceToTarget(sysUser);
//        when
        assertThat("用户名不一致", Objects.equals(sysUser.getUsername(), userDetail.getUsername()));
    }
}