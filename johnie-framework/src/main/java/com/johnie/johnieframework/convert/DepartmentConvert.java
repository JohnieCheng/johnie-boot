package com.johnie.johnieframework.convert;

import com.johnie.johniecommon.dto.DepartmentDTO;
import com.johnie.johniecommon.vo.DepartmentVo;
import com.johnie.johnieframework.entity.system.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentConvert {
  DepartmentConvert INSTANCE = Mappers.getMapper(DepartmentConvert.class);

  DepartmentVo toVo(Department user);

  DepartmentDTO toDTO(DepartmentVo userVo);

  Department toEntity(DepartmentDTO dto);
}
