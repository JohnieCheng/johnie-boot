package com.johnie.johnieframework.repository;

import com.johnie.johnieframework.entity.SysUser;
import java.util.Optional;

public interface SysUserRepository extends BaseRepository<SysUser, Long> {

  Optional<SysUser> findByEmail(String email);
}
