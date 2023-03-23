package com.johnie.johniecommon.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public abstract class AbstractAuditableBaseEntity<U> implements Serializable {
  @Id
  @SequenceGenerator(name = "seq_id", sequenceName = "seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id")
  @Column(name = "id", nullable = false)
  private Long id;

  @CreatedBy
  @Column(updatable = false)
  private U createBy;

  @CreatedDate
  @Column(updatable = false)
  private Date createDate;

  @LastModifiedBy private U updateBy;
  @LastModifiedDate private Date updateDate;
}
