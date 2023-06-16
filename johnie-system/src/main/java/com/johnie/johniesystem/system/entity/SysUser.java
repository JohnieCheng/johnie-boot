package com.johnie.johniesystem.system.entity;


import com.johnie.johnieframework.jpa.entity.AbstractAuditableBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

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
    @ToString.Exclude
    @ManyToOne(targetEntity = SysDepartment.class, fetch = FetchType.LAZY)
    private SysDepartment sysDepartment;
    private String mobile;
    private Integer status;
    private Integer superAdmin;
    @Version
    private long version;
}
