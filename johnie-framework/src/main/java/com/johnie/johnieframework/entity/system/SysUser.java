package com.johnie.johnieframework.entity.system;

import com.johnie.johniecommon.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysUser extends AbstractAuditableBaseEntity<String> implements UserDetails {
  private String email;
  private String password;

  @ToString.Exclude
  @OneToOne(
      targetEntity = SysEmployee.class,
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
  private SysEmployee employee;

  @OneToMany(
      targetEntity = SysRole.class,
      mappedBy = "user",
      fetch = FetchType.EAGER,
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
  private List<SysRole> role;

  @Version private long version;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
