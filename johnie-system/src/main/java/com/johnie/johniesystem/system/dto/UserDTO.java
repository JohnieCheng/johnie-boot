package com.johnie.johniesystem.system.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserDTO {
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
