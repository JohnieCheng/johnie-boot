package com.johnie.johniesystem.system.service;

import com.johnie.johniesystem.system.dto.RoleDTO;
import com.johnie.johniesystem.system.entity.SysRole;
import com.johnie.johniesystem.system.vo.RoleVo;

import java.util.List;
import java.util.Set;

public interface SysRoleService {
    SysRole findByNo(String roleNo);

    List<SysRole> findByNoIn(Set<String> roleNoSet);

    RoleVo addRole(RoleDTO roleDTO);
}
