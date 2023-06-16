package com.johnie.johniesystem.system.service;

import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johniesystem.system.dto.UserDTO;
import com.johnie.johniesystem.system.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

public interface SysUserService {
    UserVo getVoById(Long id);

    Page<UserVo> getUserVosByPage(Integer pageIndex, Integer pageSize);

    UserVo save(UserDTO dto);

    UserVo update(UserDTO dto);

    void delete(Long id);

    UserDetail getSysUser(String username);

    void register(UserDetail userDetail);
}
