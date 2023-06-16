package com.johnie.johniesystem.system.convert;

import com.johnie.johnieframework.common.convert.BaseConverter;
import com.johnie.johnieframework.security.user.UserDetail;
import com.johnie.johniesystem.system.dto.UserDTO;
import com.johnie.johniesystem.system.entity.SysUser;
import com.johnie.johniesystem.system.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface SysUserConvert extends BaseConverter<SysUser, UserDetail> {

//    UserDetail toUserDetail(SysUser sysUser);

    UserVo toVo(SysUser sysUser);

    UserDTO toDTO(UserVo userVo);

    SysUser toEntity(UserDTO dto);
}
