package web.uitc.uitcbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web.uitc.uitcbackend.entity.enums.Permission;
import web.uitc.uitcbackend.entity.template.AbsLongEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SystemRole extends AbsLongEntity {

    @Column(unique = true, nullable = false)
    private String name; // ADMIN, USER

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Permission> permissions;

    @Column(length = 500)
    private String description;
}
