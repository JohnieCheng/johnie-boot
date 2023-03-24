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
public class Employee extends AbstractAuditableBaseEntity<String> {
  private String name;
  private String password;
  private String email;
  private Integer age;
  private boolean admin;

  @ToString.Exclude
  @ManyToOne(
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
      fetch = FetchType.LAZY,
      targetEntity = Department.class)
  @JoinColumn(name = "department_id")
  private Department department;

  @ToString.Exclude
  @OneToOne(
      fetch = FetchType.LAZY,
      targetEntity = User.class,
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
  private User user;
}
