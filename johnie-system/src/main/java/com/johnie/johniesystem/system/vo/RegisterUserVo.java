package com.johnie.johniesystem.system.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserVo {
    private String username;
    private String password;
    private String departmentNo;
    private String roleNo;
}
