package com.johnie.johniesystem.system.entity;

import com.johnie.johnieframework.jpa.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.io.Serial;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysPermission extends AbstractAuditableBaseEntity<String> {
  @Serial
  private static final long serialVersionUID = -1210111881048045230L;
  private String expression;
  private String name;

  @ToString.Exclude
  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
      targetEntity = SysRole.class,mappedBy = "sysPermissions")
  private List<SysRole> sysRoles;
}
