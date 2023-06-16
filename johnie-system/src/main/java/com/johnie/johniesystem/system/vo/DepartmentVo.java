package com.johnie.johniesystem.system.vo;

import java.util.List;
import lombok.Data;

@Data
public class DepartmentVo {
    private Long id;
    private String name;
    private String no;
    private List<EmployeeVo> employees;
}
