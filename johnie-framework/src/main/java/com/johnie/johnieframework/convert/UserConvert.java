package com.johnie.johnieframework.convert;

import com.johnie.johniecommon.dto.UserDTO;
import com.johnie.johniecommon.vo.UserVo;
import com.johnie.johnieframework.entity.system.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
  UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

  UserVo toVo(User user);

  UserDTO toDTO(UserVo userVo);

  User toEntity(UserDTO dto);
}
