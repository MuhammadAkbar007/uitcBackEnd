package web.uitc.uitcbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web.uitc.uitcbackend.entity.template.AbsLongEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course extends AbsLongEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(length = 500)
    private String description;
}
