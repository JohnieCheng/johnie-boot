package com.johnie.johnieframework.entity.system;

import com.johnie.johniecommon.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.CascadeType;
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
public class Permission extends AbstractAuditableBaseEntity<String> {
  private String expression;
  private String name;

  @ToString.Exclude
  @ManyToMany(
      mappedBy = "permissions",
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
      targetEntity = Role.class)
  private List<Role> roles;
}
