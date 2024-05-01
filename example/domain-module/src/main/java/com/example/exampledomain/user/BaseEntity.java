package com.example.exampledomain.user;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    private Long createDateTime;
    @LastModifiedDate
    private Long lastModifiedDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String modifiedBy;

    public BaseEntity (String userName) {
        this.createdBy = userName;
        this.modifiedBy = userName;
    }
}
