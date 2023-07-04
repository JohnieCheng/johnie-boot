package com.johnie.johniesystem.system.convert;

import com.johnie.johnieframework.common.convert.BaseConverter;
import com.johnie.johniesystem.system.dto.RoleDTO;
import com.johnie.johniesystem.system.entity.SysRole;
import com.johnie.johniesystem.system.vo.RoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysRoleConvert extends BaseConverter<SysRole, RoleVo> {
//    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

//    RoleVo toVo(SysRole sysUser);

    RoleDTO toDTO(RoleVo roleVo);

    SysRole toEntity(RoleDTO dto);
}
