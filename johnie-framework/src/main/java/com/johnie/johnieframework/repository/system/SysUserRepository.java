package com.johnie.johnieframework.repository.system;

import com.johnie.johniecommon.repository.BaseRepository;
import com.johnie.johnieframework.entity.system.SysUser;
import java.util.Optional;

public interface SysUserRepository extends BaseRepository<SysUser, Long> {

  Optional<SysUser> findByEmail(String email);
}
