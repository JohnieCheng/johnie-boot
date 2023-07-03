package com.johnie.johniesystem.system.repository;

import com.johnie.johnieframework.jpa.repository.BaseRepository;
import com.johnie.johniesystem.system.entity.SysPermission;
import com.johnie.johniesystem.system.entity.SysRole;

import java.util.List;
import java.util.Set;

public interface SysPermissionRepository extends BaseRepository<SysPermission, Long> {
    List<SysPermission> findInNos(Set<String> permissionNos);
}
