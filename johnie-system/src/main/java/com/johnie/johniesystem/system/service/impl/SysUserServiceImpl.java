package com.johnie.johniesystem.system.service.impl;

import com.johnie.johnieframework.common.exception.ErrorCode;
import com.johnie.johnieframework.common.exception.ServerException;
import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johniesystem.system.entity.SysDepartment;
import com.johnie.johniesystem.system.entity.SysPermission;
import com.johnie.johniesystem.system.entity.SysRole;
import com.johnie.johniesystem.system.entity.SysUser;
import com.johnie.johniesystem.system.convert.SysUserConvert;
import com.johnie.johniesystem.system.dto.UserDTO;

import com.johnie.johniesystem.system.repository.SysUserRepository;
import com.johnie.johniesystem.system.service.SysDepartmentService;
import com.johnie.johniesystem.system.service.SysRoleService;
import com.johnie.johniesystem.system.service.SysUserService;
import com.johnie.johniesystem.system.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    final SysUserRepository userRepository;
    final SysUserConvert sysUserConvert;
    final SysDepartmentService sysDepartmentService;
    final SysRoleService sysRoleService;

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
    @Transactional
    public UserVo update(UserDTO dto) {
        Set<String> roleNoSet = Arrays.stream(dto.getSysRoleNos().split(",")).collect(Collectors.toSet());
        String departmentNo = dto.getSysDepartmentNo();
        List<SysRole> sysRoles = sysRoleService.findByNoIn(roleNoSet);
        SysDepartment sysDepartment = sysDepartmentService.findByNo(departmentNo);
        SysUser sysUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new ServerException(ErrorCode.USER_ACCOUNT_NOT_EXIST));
        sysUser.setSysRoles(sysRoles);
        sysUser.setSysDepartment(sysDepartment);
        SysUser save = userRepository.saveAndFlush(sysUser);
        return sysUserConvert.toVo(save);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetail loadUserByUsername(String username) {
        SysUser sysUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));
        UserDetail userDetail = sysUserConvert.sourceToTarget(sysUser);
        Set<String> authoritySet = sysUser.getSysRoles().stream().flatMap(sysRole -> sysRole.getSysPermissions().stream())
                .map(SysPermission::getExpression).collect(Collectors.toSet());
        userDetail.setAuthoritySet(authoritySet);
        return userDetail;
    }

    @Override
    public void register(UserDetail userDetail) {
//        SysUser sysUser = sysUserConvert.targetToSource(userDetail);
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userDetail.getUsername());
        sysUser.setPassword(userDetail.getPassword());
        String departmentNo = userDetail.getDepartmentNo();
        SysDepartment sysDepartment = sysDepartmentService.findByNo(departmentNo);
        sysUser.setSysDepartment(sysDepartment);
        String roleNo = userDetail.getRoleNo();
        Set<String> roleNoSet = Arrays.stream(roleNo.split(",")).collect(Collectors.toSet());
        List<SysRole> sysRoles = sysRoleService.findByNoIn(roleNoSet);
        sysUser.setSysRoles(sysRoles);
        sysUser.setStatus(1);
        userRepository.save(sysUser);
    }
}
