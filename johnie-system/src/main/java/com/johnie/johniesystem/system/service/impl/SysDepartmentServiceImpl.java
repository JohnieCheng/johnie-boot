package com.johnie.johniesystem.system.service.impl;

import com.johnie.johnieframework.common.exception.ErrorCode;
import com.johnie.johnieframework.common.exception.ServerException;
import com.johnie.johniesystem.system.entity.SysDepartment;
import com.johnie.johniesystem.system.repository.SysDepartmentRepository;
import com.johnie.johniesystem.system.service.SysDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysDepartmentServiceImpl implements SysDepartmentService {
    final SysDepartmentRepository repository;

    @Override
    public SysDepartment findByNo(String departmentNo) {
        return repository.findByNo(departmentNo).orElseThrow(() -> new ServerException(ErrorCode.PARAM_TYPE_ERROR));
    }
}
