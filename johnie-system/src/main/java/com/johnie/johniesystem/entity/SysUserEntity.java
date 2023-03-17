package com.johnie.johniesystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractAuditable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class SysUserEntity extends AbstractAuditable<String, Long> {
  private String userName;
  private String password;

  @Version private long version;
}
