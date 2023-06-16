package com.johnie.johniesystem.system.vo;

import java.util.List;
import lombok.Data;

@Data
public class UserVo {
  private Long id;
  private String email;
  private String password;
  private List<RoleVo> role;
  private EmployeeVo employee;
  private long version;
}
