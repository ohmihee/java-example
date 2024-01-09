package mihee.com.board.models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    @CreatedBy
    protected String createdBy;
    @CreationTimestamp
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;
    @LastModifiedBy
    protected String lastModifiedBy;
    @UpdateTimestamp
    @LastModifiedDate
    protected LocalDateTime lastModifiedAt;

}
