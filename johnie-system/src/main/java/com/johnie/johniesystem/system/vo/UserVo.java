package com.johnie.johniesystem.system.vo;

import java.util.List;

import com.johnie.johniesystem.system.entity.SysDepartment;
import com.johnie.johniesystem.system.entity.SysRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
public class UserVo {
    private Long id;
    private String email;
    private String username;
    private String realName;
    private String password;
    private String avatar;
    private String mobile;
    private Integer status;
    private Integer superAdmin;
    private String sysDepartmentNo;
    private String sysRoleNos;
    private long version;
}
