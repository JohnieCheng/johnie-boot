package com.johnie.johnieframework.service.system;

import com.johnie.johniecommon.dto.UserDTO;
import com.johnie.johniecommon.vo.UserVo;
import org.springframework.data.domain.Page;

public interface SysUserService {
  UserVo getVoById(Long id);

  Page<UserVo> getUserVosByPage(Integer pageIndex, Integer pageSize);

  UserVo save(UserDTO dto);

  UserVo update(UserDTO dto);
void delete(Long id);
}
