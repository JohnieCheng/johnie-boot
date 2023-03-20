package com.johnie.johnieframework.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SysPermission extends AbstractAuditableBaseEntity<String> {
    private String expression;
    private String name;
    
}
