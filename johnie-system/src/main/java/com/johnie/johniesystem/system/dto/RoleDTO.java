package com.johnie.johniesystem.system.dto;

import java.util.List;
import lombok.Data;

@Data
public class RoleDTO {
  private Long id;
  private String name;
  private String no;
  private List<PermissionDTO> permissions;
  private UserDTO user;
}
