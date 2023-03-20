package com.johnie.johnieframework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.List;
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
  @ManyToOne private SysDepartment department;
  @ManyToMany private List<SysRole> sysRoles;
}
