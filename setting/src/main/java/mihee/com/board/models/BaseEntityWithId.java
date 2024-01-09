package mihee.com.board.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntityWithId implements Serializable {
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;
    @CreatedBy
    protected String createdBy;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected LocalDateTime createdAt;
    @LastModifiedBy
    protected String lastModifiedBy;
    @UpdateTimestamp
    @LastModifiedDate
    protected LocalDateTime lastModifiedAt;




}

