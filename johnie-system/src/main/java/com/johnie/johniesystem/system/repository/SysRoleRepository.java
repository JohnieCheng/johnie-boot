package com.johnie.johniesystem.system.repository;

import com.johnie.johnieframework.jpa.repository.BaseRepository;
import com.johnie.johniesystem.system.entity.SysRole;
import org.springframework.data.jpa.repository.Meta;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SysRoleRepository extends BaseRepository<SysRole, Long> {
    @Meta(comment = "根据编号查找部门")
    Optional<SysRole> findByNo(String roleNo);

    List<SysRole> findByNoIn(Set<String> roleNoSet);
}
