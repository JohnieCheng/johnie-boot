package com.johnie.johniecommon.vo;

import lombok.Data;import java.util.List;

@Data
public class PermissionVo {
  private String expression;
  private String name;

  private List<RoleVo> roles;
}
