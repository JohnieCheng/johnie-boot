package com.johnie.johniesystem.system.service.impl;

import com.johnie.johnieframework.common.exception.ErrorCode;
import com.johnie.johnieframework.common.exception.ServerException;
import com.johnie.johniesystem.system.convert.SysRoleConvert;
import com.johnie.johniesystem.system.dto.RoleDTO;
import com.johnie.johniesystem.system.entity.SysPermission;
import com.johnie.johniesystem.system.entity.SysRole;
import com.johnie.johniesystem.system.repository.SysPermissionRepository;
import com.johnie.johniesystem.system.repository.SysRoleRepository;
import com.johnie.johniesystem.system.service.SysRoleService;
import com.johnie.johniesystem.system.vo.RoleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    final SysRoleRepository repository;
    final SysPermissionRepository sysPermissionRepository;
    final SysRoleConvert sysRoleConvert;

    @Override
    public SysRole findByNo(String roleNo) {
        return repository.findByNo(roleNo).orElseThrow(() -> new ServerException(ErrorCode.PARAM_TYPE_ERROR));
    }

    @Override
    public List<SysRole> findByNoIn(Set<String> roleNoSet) {
        return repository.findByNoIn(roleNoSet);
    }

    @Override
    @Transactional
    public RoleVo addRole(RoleDTO roleDTO) {
        SysRole sysRole = sysRoleConvert.toEntity(roleDTO);
        Set<String> permissionNos = Arrays.stream(roleDTO.getPermissionNos().split(",")).collect(Collectors.toSet());
        List<SysPermission> sysPermissions = sysPermissionRepository.findInNos(permissionNos);
        sysRole.setSysPermissions(sysPermissions);
        SysRole savedRole = repository.save(sysRole);
        return sysRoleConvert.toVo(savedRole);
    }
}
