package com.johnie.johnieframework.entity.system;

import com.johnie.johniecommon.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysRole extends AbstractAuditableBaseEntity<String> {

  private String name;
  private String no;

  @ToString.Exclude
  @ManyToMany(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
      targetEntity = SysPermission.class)
  private List<SysPermission> permissions;

  @ManyToOne(targetEntity = SysUser.class, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_fk")
  private SysUser user;
  //  @JoinTable(
  //      name = "sys_role_sys_permission", // 中间表的名称
  //      // 中间表teacher_users的字段teachers_id关联teacher表的主键字段id
  //      joinColumns = {@JoinColumn(name = "teachers_id", referencedColumnName = "id")},
  //      // 中间表teacher_users的字段users_id关联user表的主键id
  //      inverseJoinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")})

}
