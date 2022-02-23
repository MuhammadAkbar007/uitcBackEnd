package web.uitc.uitcbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import web.uitc.uitcbackend.entity.template.AbsLongEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attachment extends AbsLongEntity {
    private String fileOriginalName; /* pdp.jpg, inn.pdf */
    private long size; /* 2048000 */
    private String contentType; /* application/pdf, image/png */

    /* fs da saqlanadigan nomi */
    private String name; /* file system uchun kerak */
}
