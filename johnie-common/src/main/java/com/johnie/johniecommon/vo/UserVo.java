package com.johnie.johniecommon.vo;

import java.util.List;
import lombok.Data;

@Data
public class UserVo {
  private String email;
  private List<RoleVo> role;
}
