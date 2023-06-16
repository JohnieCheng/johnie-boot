package com.johnie.johniesystem.system.vo;

import lombok.Data;

@Data
public class EmployeeVo {
  private Long id;
  private String name;
  private String password;
  private String email;
  private Integer age;
  private boolean admin;

  private DepartmentVo department;
  private UserVo user;
}
