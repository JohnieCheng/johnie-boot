package com.johnie.johniesystem.system.convert;

import com.johnie.johnieframework.common.convert.BaseConverter;
import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johniesystem.system.dto.UserDTO;
import com.johnie.johniesystem.system.entity.SysUser;
import com.johnie.johniesystem.system.vo.UserVo;
import org.mapstruct.Mapper;

@Mapper
public interface SysUserConvert extends BaseConverter<SysUser, UserDetail> {

//    UserDetail toUserDetail(SysUser sysUser);

    UserVo toVo(SysUser sysUser);

    UserDTO toDTO(UserVo userVo);

    SysUser toEntity(UserDTO dto);
}
