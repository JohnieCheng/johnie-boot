package com.johnie.johnieframework.controller.system;

import com.johnie.johniecommon.dto.UserDTO;
import com.johnie.johniecommon.vo.UserVo;
import com.johnie.johnieframework.convert.UserConvert;
import com.johnie.johnieframework.service.system.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class UserController {
  final SysUserService sysUserService;

  @GetMapping("/{id}")
  public ResponseEntity<UserVo> getUser(@PathVariable Long id) {
    UserVo user = this.sysUserService.getVoById(id);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }

  @GetMapping()
  public ResponseEntity<Page<UserVo>> getUsersByPage(
      @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
    Page<UserVo> userPages = this.sysUserService.getUserVosByPage(pageIndex, pageSize);
    return ResponseEntity.status(HttpStatus.OK).body(userPages);
  }

  @PostMapping()
  public ResponseEntity<UserVo> getUsers(@RequestBody UserVo userVo) {
    UserDTO dto = UserConvert.INSTANCE.toDTO(userVo);
    UserVo user = this.sysUserService.save(dto);
    return ResponseEntity.status(HttpStatus.OK).body(user);
  }
}
