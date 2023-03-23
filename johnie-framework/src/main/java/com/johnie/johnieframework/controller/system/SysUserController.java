package com.johnie.johnieframework.controller.system;

import com.johnie.johniecommon.vo.UserVo;
import com.johnie.johnieframework.service.system.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class SysUserController {
  final SysUserService sysUserService;

  @GetMapping("/{id}")
  public ResponseEntity<UserVo> getUser(@PathVariable Long id) {
    UserVo user = this.sysUserService.getVoById(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }
}
