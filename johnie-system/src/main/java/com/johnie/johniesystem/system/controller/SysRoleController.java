package com.johnie.johniesystem.system.controller;

import com.johnie.johniesystem.system.convert.SysRoleConvert;
import com.johnie.johniesystem.system.dto.RoleDTO;
import com.johnie.johniesystem.system.service.SysRoleService;
import com.johnie.johniesystem.system.vo.RoleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys-role")
@RequiredArgsConstructor
public class SysRoleController {
    final SysRoleService sysRoleService;
    final SysRoleConvert sysRoleConvert;

    @PostMapping
    public ResponseEntity<RoleVo> addRole(@RequestBody RoleVo roleVo) {
        RoleDTO roleDTO = sysRoleConvert.toDTO(roleVo);
        RoleVo addRole = sysRoleService.addRole(roleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(addRole);
    }
}
