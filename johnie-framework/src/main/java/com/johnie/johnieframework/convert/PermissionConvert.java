package com.johnie.johnieframework.convert;

import com.johnie.johniecommon.dto.PermissionDTO;
import com.johnie.johniecommon.vo.PermissionVo;
import com.johnie.johnieframework.entity.system.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionConvert {
  PermissionConvert INSTANCE = Mappers.getMapper(PermissionConvert.class);

  PermissionVo toVo(Permission user);

  PermissionDTO toDTO(PermissionVo userVo);

  Permission toEntity(PermissionDTO dto);
}
