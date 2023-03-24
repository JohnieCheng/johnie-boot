package com.johnie.johniecommon.vo;

import java.util.List;
import lombok.Data;

@Data
public class RoleVo {
  private Long id;
  private String name;
  private String no;
  private List<PermissionVo> permissions;
  private UserVo user;
}
