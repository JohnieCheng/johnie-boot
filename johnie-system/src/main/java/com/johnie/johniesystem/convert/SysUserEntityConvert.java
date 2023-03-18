package com.johnie.johniesystem.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserEntityConvert {
  SysUserEntityConvert INSTANCE = Mappers.getMapper(SysUserEntityConvert.class);
}
