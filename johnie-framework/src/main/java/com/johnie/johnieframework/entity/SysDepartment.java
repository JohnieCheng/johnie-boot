package com.johnie.johnieframework.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysDepartment extends AbstractAuditableBaseEntity<String> {
  private String name;
  private String no;
}
