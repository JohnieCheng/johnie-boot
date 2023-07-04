package com.johnie.johniesystem.system.repository;

import com.johnie.johnieframework.jpa.repository.BaseRepository;
import com.johnie.johniesystem.system.entity.SysPermission;

import java.util.List;
import java.util.Set;

public interface SysPermissionRepository extends BaseRepository<SysPermission, Long> {
    List<SysPermission> findByNoIn(Set<String> permissionNos);
}
