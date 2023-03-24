package com.johnie.johnieframework.entity.system;

import com.johnie.johniecommon.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department extends AbstractAuditableBaseEntity<String> {
  private String name;
  private String no;

  @ToString.Exclude
  @OneToMany(
      mappedBy = "department",
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
      fetch = FetchType.LAZY,
      targetEntity = Employee.class)
  private List<Employee> employees;
}
