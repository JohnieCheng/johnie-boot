package com.johnie.johniesystem.system.controller;

import com.johnie.johniesystem.system.convert.SysPermissionConvert;
import com.johnie.johniesystem.system.dto.PermissionDTO;
import com.johnie.johniesystem.system.service.SysPermissionService;
import com.johnie.johniesystem.system.vo.PermissionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sys-permission")
@RequiredArgsConstructor
public class SysPermissionController {
    final SysPermissionService sysPermissionService;
    final SysPermissionConvert sysPermissionConvert;

    @PostMapping
    public ResponseEntity<Long> addRole(@RequestBody PermissionVo permissionVo) {
        PermissionDTO permissionDTO = sysPermissionConvert.toDTO(permissionVo);
        Long id = sysPermissionService.addPermission(permissionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
