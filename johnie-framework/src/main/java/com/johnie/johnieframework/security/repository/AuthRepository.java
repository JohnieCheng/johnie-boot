package com.johnie.johnieframework.security.repository;

import com.johnie.johniecommon.repository.BaseRepository;
import com.johnie.johnieframework.entity.system.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Meta;

public interface AuthRepository extends BaseRepository<User, Long> {

  @Meta(comment = "根据邮箱查找用户")
  Optional<User> findByEmail(String email);
}
