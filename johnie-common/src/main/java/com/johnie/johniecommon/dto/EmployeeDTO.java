package com.johnie.johniecommon.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
  private Long id;
  private String name;
  private String password;
  private String email;
  private Integer age;
  private boolean admin;
  private DepartmentDTO department;
  private UserDTO user;
}
