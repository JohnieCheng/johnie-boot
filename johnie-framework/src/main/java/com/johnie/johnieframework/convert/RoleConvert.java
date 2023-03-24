package com.johnie.johnieframework.convert;

import com.johnie.johniecommon.dto.RoleDTO;
import com.johnie.johniecommon.vo.RoleVo;
import com.johnie.johnieframework.entity.system.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {
  RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

  RoleVo toVo(Role user);

  RoleDTO toDTO(RoleVo userVo);

  Role toEntity(RoleDTO dto);
}
