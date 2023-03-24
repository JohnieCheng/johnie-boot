package com.johnie.johnieframework.service.system.impl;

import com.johnie.johniecommon.dto.UserDTO;
import com.johnie.johniecommon.enums.ErrorCode;
import com.johnie.johniecommon.exception.ServerException;
import com.johnie.johniecommon.vo.UserVo;
import com.johnie.johnieframework.convert.UserConvert;
import com.johnie.johnieframework.entity.system.User;
import com.johnie.johnieframework.repository.system.SysUserRepository;
import com.johnie.johnieframework.service.system.SysUserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
  final SysUserRepository userRepository;

  @Override
  public UserVo getVoById(Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    User user = userOptional.orElseThrow(() -> new ServerException("用户不存在"));
    return UserConvert.INSTANCE.toVo(user);
  }

  @Override
  public Page<UserVo> getUserVosByPage(Integer pageIndex, Integer pageSize) {
    Pageable pageable = PageRequest.of(pageIndex, pageSize);
    Page<User> userPage = userRepository.findAll(pageable);
    return userPage.map(UserConvert.INSTANCE::toVo);
  }

  @Override
  public UserVo save(UserDTO dto) {
    User sysUser = UserConvert.INSTANCE.toEntity(dto);
    User user = userRepository.save(sysUser);
    return UserConvert.INSTANCE.toVo(user);
  }

  @Override
  public UserVo update(UserDTO dto) {
    User user = UserConvert.INSTANCE.toEntity(dto);
    Long id = dto.getId();
    User userEntity =
        userRepository
            .findById(id)
            .orElseThrow(() -> new ServerException(ErrorCode.USER_ACCOUNT_NOT_EXIST));
    //    BeanUtils.copyProperties(dto,userEntity);
    userEntity.setRole(user.getRole());
    userEntity.setEmployee(user.getEmployee());
    return UserConvert.INSTANCE.toVo(userEntity);
  }

  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
  }
}
