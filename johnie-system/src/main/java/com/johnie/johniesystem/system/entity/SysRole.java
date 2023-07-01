package com.johnie.johniesystem.system.entity;

import com.johnie.johnieframework.jpa.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.*;

import java.io.Serial;
import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysRole extends AbstractAuditableBaseEntity<String> {

    @Serial
    private static final long serialVersionUID = -1989667621887695799L;
    private String no;
    private String name;

    @ToString.Exclude
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
            targetEntity = SysPermission.class)
    private List<SysPermission> sysPermissions;
    @ToString.Exclude
    @ManyToMany(
            cascade = {CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            targetEntity = SysUser.class,mappedBy = "sysRoles")
    private List<SysUser> sysUsers;

}
