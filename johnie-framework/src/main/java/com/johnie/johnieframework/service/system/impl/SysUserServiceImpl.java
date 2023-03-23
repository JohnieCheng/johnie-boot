package com.johnie.johnieframework.service.system.impl;

import com.johnie.johniecommon.exception.ServerException;
import com.johnie.johniecommon.vo.UserVo;
import com.johnie.johnieframework.convert.SysUserConvert;
import com.johnie.johnieframework.entity.system.SysUser;
import com.johnie.johnieframework.repository.system.SysUserRepository;
import com.johnie.johnieframework.service.system.SysUserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
  final SysUserRepository userRepository;

  @Override
  public UserVo getVoById(Long id) {
    Optional<SysUser> userOptional = userRepository.findById(id);
    SysUser user = userOptional.orElseThrow(() -> new ServerException("用户不存在"));
    return SysUserConvert.INSTANCE.toVo(user);
  }
}
