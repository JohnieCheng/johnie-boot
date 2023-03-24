package com.johnie.johnieframework.repository.system;

import com.johnie.johniecommon.repository.BaseRepository;
import com.johnie.johnieframework.entity.system.User;
import java.util.Optional;

public interface SysUserRepository extends BaseRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
