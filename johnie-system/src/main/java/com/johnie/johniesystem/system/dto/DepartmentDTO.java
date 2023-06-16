package com.johnie.johniesystem.system.dto;

import java.util.List;
import lombok.Data;

@Data
public class DepartmentDTO {
  private Long id;
  private String name;
  private String no;
  private List<EmployeeDTO> employees;
}
