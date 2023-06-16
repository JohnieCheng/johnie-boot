package com.johnie.johniesystem.system.service.impl;

import com.johnie.johnieframework.common.exception.ErrorCode;
import com.johnie.johnieframework.common.exception.ServerException;
import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johniesystem.system.entity.SysUser;
import com.johnie.johniesystem.system.convert.SysUserConvert;
import com.johnie.johniesystem.system.dto.UserDTO;

import com.johnie.johniesystem.system.repository.SysUserRepository;
import com.johnie.johniesystem.system.service.SysUserService;
import com.johnie.johniesystem.system.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    final SysUserRepository userRepository;
    final SysUserConvert sysUserConvert;

    @Override
    public UserVo getVoById(Long id) {
        Optional<SysUser> userOptional = userRepository.findById(id);
        SysUser sysUser = userOptional.orElseThrow(() -> new ServerException("用户不存在"));
        return sysUserConvert.toVo(sysUser);
    }

    @Override
    public Page<UserVo> getUserVosByPage(Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<SysUser> userPage = userRepository.findAll(pageable);
        return userPage.map(sysUserConvert::toVo);
    }

    @Override
    public UserVo save(UserDTO dto) {
        SysUser sysUser = sysUserConvert.toEntity(dto);
        SysUser user = userRepository.save(sysUser);
        return sysUserConvert.toVo(user);
    }

    @Override
    public UserVo update(UserDTO dto) {
        SysUser sysUser = sysUserConvert.toEntity(dto);
        Long id = dto.getId();
        SysUser sysUserEntity =
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new ServerException(ErrorCode.USER_ACCOUNT_NOT_EXIST));
        //    BeanUtils.copyProperties(dto,userEntity);
//    sysUserEntity.setRole(sysUser.getRole());
//    sysUserEntity.setEmployee(sysUser.getEmployee());
        return sysUserConvert.toVo(sysUserEntity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetail getSysUser(String username) {
        SysUser sysUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
        return sysUserConvert.sourceToTarget(sysUser);
    }

    @Override
    public void register(UserDetail userDetail) {
        SysUser sysUser = sysUserConvert.targetToSource(userDetail);
        sysUser.setStatus(1);
        userRepository.save(sysUser);
    }
}
