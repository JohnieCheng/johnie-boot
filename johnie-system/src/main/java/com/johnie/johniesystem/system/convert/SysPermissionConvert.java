package com.johnie.johniesystem.system.convert;

import com.johnie.johnieframework.common.convert.BaseConverter;
import com.johnie.johniesystem.system.dto.PermissionDTO;
import com.johnie.johniesystem.system.dto.RoleDTO;
import com.johnie.johniesystem.system.entity.SysPermission;
import com.johnie.johniesystem.system.entity.SysRole;
import com.johnie.johniesystem.system.vo.PermissionVo;
import com.johnie.johniesystem.system.vo.RoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SysPermissionConvert extends BaseConverter<SysPermission, PermissionVo> {
    PermissionDTO toDTO(PermissionVo roleVo);

    SysPermission toEntity(PermissionDTO dto);
}
