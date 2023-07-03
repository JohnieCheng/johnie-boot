package com.johnie.johniesystem.system.convert;

import com.johnie.johnieframework.common.convert.BaseConverter;
import com.johnie.johniesystem.system.dto.RoleDTO;
import com.johnie.johniesystem.system.dto.UserDTO;
import com.johnie.johniesystem.system.entity.SysRole;
import com.johnie.johniesystem.system.entity.SysUser;
import com.johnie.johniesystem.system.vo.RoleVo;
import com.johnie.johniesystem.system.vo.UserVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface SysRoleConvert extends BaseConverter<SysRole, RoleVo> {
    RoleVo toVo(SysRole sysUser);

    RoleDTO toDTO(RoleVo roleVo);

    SysRole toEntity(RoleDTO dto);
}
