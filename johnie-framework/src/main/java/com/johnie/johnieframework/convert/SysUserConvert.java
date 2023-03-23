package com.johnie.johnieframework.convert;

import com.johnie.johniecommon.vo.UserVo;
import com.johnie.johnieframework.entity.system.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {
  SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

  UserVo toVo(SysUser user);
}
