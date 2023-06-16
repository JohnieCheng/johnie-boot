package com.johnie.johniesystem.system.dto;

import java.util.List;
import lombok.Data;

@Data
public class PermissionDTO {
  private String expression;
  private String name;

  private List<RoleDTO> roles;
}
