package com.johnie.johniesystem.system.repository;

import com.johnie.johniesystem.system.entity.SysUser;
import com.johnie.johnieframework.jpa.repository.BaseRepository;

import java.util.Optional;

public interface SysUserRepository extends BaseRepository<SysUser, Long> {

  Optional<SysUser> findByEmail(String email);
}
