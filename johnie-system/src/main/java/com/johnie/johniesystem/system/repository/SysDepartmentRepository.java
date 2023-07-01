package com.johnie.johniesystem.system.repository;

import com.johnie.johnieframework.jpa.repository.BaseRepository;
import com.johnie.johniesystem.system.entity.SysDepartment;
import com.johnie.johniesystem.system.entity.SysUser;
import org.springframework.data.jpa.repository.Meta;

import java.util.Optional;

public interface SysDepartmentRepository extends BaseRepository<SysDepartment, Long> {

    @Meta(comment = "根据编号查找部门")
    Optional<SysDepartment> findByNo(String departmentNo);
}
