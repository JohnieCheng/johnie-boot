package com.johnie.johniesystem.system.service.impl;

import com.johnie.johnieframework.common.exception.ErrorCode;
import com.johnie.johnieframework.common.exception.ServerException;
import com.johnie.johniesystem.system.entity.SysDepartment;
import com.johnie.johniesystem.system.entity.SysRole;
import com.johnie.johniesystem.system.repository.SysDepartmentRepository;
import com.johnie.johniesystem.system.repository.SysRoleRepository;
import com.johnie.johniesystem.system.service.SysDepartmentService;
import com.johnie.johniesystem.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    final SysRoleRepository repository;

    @Override
    public SysRole findByNo(String roleNo) {
        return repository.findByNo(roleNo).orElseThrow(() -> new ServerException(ErrorCode.PARAM_TYPE_ERROR));
    }

    @Override
    public List<SysRole> findByNoIn(Set<String> roleNoSet) {
        return repository.findByNoIn(roleNoSet);
    }
}
