package com.johnie.johniesystem.system.entity;

import com.johnie.johnieframework.jpa.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.io.Serial;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysDepartment extends AbstractAuditableBaseEntity<String> {
  @Serial
  private static final long serialVersionUID = 701039509193391517L;
  private String name;
  private String no;

  @ToString.Exclude
  @OneToMany(
      mappedBy = "sysDepartment",
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
      fetch = FetchType.LAZY,
      targetEntity = SysUser.class)
  private List<SysUser> sysUsers;
}
