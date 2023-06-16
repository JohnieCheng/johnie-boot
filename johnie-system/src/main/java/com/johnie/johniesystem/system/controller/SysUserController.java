package com.johnie.johniesystem.system.controller;

import com.johnie.johniesystem.system.dto.UserDTO;
import com.johnie.johniesystem.system.vo.UserVo;
import com.johnie.johniesystem.system.convert.SysUserConvert;
import com.johnie.johniesystem.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/sys-user")
public class SysUserController {
    final SysUserService sysUserService;
    final SysUserConvert sysUserConvert;

    @GetMapping("/{id}")
    public ResponseEntity<UserVo> getUser(@PathVariable Long id) {
        UserVo user = sysUserService.getVoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping()
    public ResponseEntity<Page<UserVo>> getUsersByPage(
            @RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        Page<UserVo> userPages = sysUserService.getUserVosByPage(pageIndex, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(userPages);
    }

    @PostMapping()
    public ResponseEntity<UserVo> addUser(@RequestBody UserVo userVo) {
        UserDTO dto = sysUserConvert.toDTO(userVo);
        UserVo user = sysUserService.save(dto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping()
    public ResponseEntity<UserVo> updateUser(@RequestBody UserVo userVo) {
        UserDTO dto = sysUserConvert.toDTO(userVo);
        UserVo user = sysUserService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        sysUserService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
