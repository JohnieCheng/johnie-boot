package com.johnie.johnieframework.security.repository;

import com.johnie.johniecommon.repository.BaseRepository;
import com.johnie.johnieframework.entity.system.SysUser;
import java.util.Optional;

public interface SysAuthRepository extends BaseRepository<SysUser, Long> {

  Optional<SysUser> findByEmail(String email);
}
