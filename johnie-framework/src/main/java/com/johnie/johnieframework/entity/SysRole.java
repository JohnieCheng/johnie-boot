package com.johnie.johnieframework.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
  @ManyToMany
  private List<SysPermission> sysPermissions;
}
