package com.johnie.johniesystem.system.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserDTO {
  private Long id;
  private String email;
  private String password;
  private List<RoleDTO> role;
  private EmployeeDTO employee;
  private long version;
}
