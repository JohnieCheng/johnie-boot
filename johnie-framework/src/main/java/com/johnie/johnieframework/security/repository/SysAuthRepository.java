package com.johnie.johnieframework.security.repository;

import com.johnie.johniecommon.repository.BaseRepository;
import com.johnie.johnieframework.entity.system.SysUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.Meta;

public interface SysAuthRepository extends BaseRepository<SysUser, Long> {

  @Meta(comment = "根据邮箱查找用户")
  Optional<SysUser> findByEmail(String email);
}
