package com.johnie.johniesystem.system.service.impl;

import com.johnie.johniesystem.system.convert.SysPermissionConvert;
import com.johnie.johniesystem.system.dto.PermissionDTO;
import com.johnie.johniesystem.system.entity.SysPermission;
import com.johnie.johniesystem.system.repository.SysPermissionRepository;
import com.johnie.johniesystem.system.service.SysPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysPermissionServiceImpl implements SysPermissionService {
    final SysPermissionRepository repository;
    final SysPermissionConvert sysPermissionConvert;

    @Override
    public Long addPermission(PermissionDTO permissionDTO) {
        SysPermission sysPermission = sysPermissionConvert.toEntity(permissionDTO);
        SysPermission save = repository.save(sysPermission);
        return save.getId();
    }
}
