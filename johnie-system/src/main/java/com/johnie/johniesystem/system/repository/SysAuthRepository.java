package com.johnie.johniesystem.system.repository;

import com.johnie.johnieframework.jpa.repository.BaseRepository;
import com.johnie.johniesystem.system.entity.SysUser;
import org.springframework.data.jpa.repository.Meta;

import java.util.Optional;

public interface SysAuthRepository extends BaseRepository<SysUser, Long> {

    @Meta(comment = "根据邮箱查找用户")
    Optional<SysUser> findByUsername(String email);
}
