package com.johnie.johnieframework.repository;

import com.johnie.johnieframework.entity.SysUserEntity;
import java.util.Optional;

public interface SysUserRepository extends BaseRepository<SysUserEntity, Long> {

  Optional<SysUserEntity> findByEmail(String email);
}
