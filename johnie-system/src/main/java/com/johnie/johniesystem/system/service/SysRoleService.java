package com.johnie.johniesystem.system.service;

import com.johnie.johniesystem.system.entity.SysRole;

import java.util.List;
import java.util.Set;

public interface SysRoleService {
    SysRole findByNo(String roleNo);

    List<SysRole> findByNoIn(Set<String> roleNoSet);
}
