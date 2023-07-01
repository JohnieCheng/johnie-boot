package com.johnie.johniesystem.system.entity;


import com.johnie.johnieframework.jpa.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;
import java.io.Serial;
import java.util.List;
import java.util.Set;

/**
 * 登录用户信息
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysUser extends AbstractAuditableBaseEntity<String> {
    @Serial
    private static final long serialVersionUID = -3158663105647866822L;
    private String email;
    private String username;
    private String realName;
    private String password;
    private String avatar;
    private String mobile;
    private Integer status;
    private Integer superAdmin;
    @ToString.Exclude
    @ManyToOne(targetEntity = SysDepartment.class, fetch = FetchType.LAZY)
    private SysDepartment sysDepartment;
    /**
     * 拥有权限集合
     */
//    @ElementCollection(fetch = FetchType.EAGER)
//    private Set<String> authoritySet;
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH},
            targetEntity = SysRole.class)
    private List<SysRole> sysRoles;
    @Version
    private long version;
}
