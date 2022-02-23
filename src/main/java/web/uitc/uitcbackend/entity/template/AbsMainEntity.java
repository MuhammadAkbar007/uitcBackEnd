package web.uitc.uitcbackend.entity.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import web.uitc.uitcbackend.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsMainEntity {

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;

    @JoinColumn(updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    @ToString.Exclude
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @LastModifiedBy
    @ToString.Exclude
    private User updatedBy;
}
