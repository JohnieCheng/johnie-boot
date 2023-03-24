package com.johnie.johnieframework.convert;

import com.johnie.johniecommon.dto.EmployeeDTO;
import com.johnie.johniecommon.vo.EmployeeVo;
import com.johnie.johnieframework.entity.system.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeConvert {
  EmployeeConvert INSTANCE = Mappers.getMapper(EmployeeConvert.class);

  EmployeeVo toVo(Employee user);

  EmployeeDTO toDTO(EmployeeVo userVo);

  Employee toEntity(EmployeeDTO dto);
}
