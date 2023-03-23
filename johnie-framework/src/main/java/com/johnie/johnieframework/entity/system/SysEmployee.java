package com.johnie.johnieframework.entity.system;

import com.johnie.johniecommon.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysEmployee extends AbstractAuditableBaseEntity<String> {
  private String name;
  private String password;
  private String email;
  private Integer age;
  private boolean admin;

  @ToString.Exclude
  @ManyToOne(
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
      fetch = FetchType.LAZY,
      targetEntity = SysDepartment.class)
  @JoinColumn(name = "department_fk")
  private SysDepartment department;

  @ToString.Exclude
  @OneToOne(
      mappedBy = "employee",
      targetEntity = SysUser.class,
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
  private SysUser user;
}
