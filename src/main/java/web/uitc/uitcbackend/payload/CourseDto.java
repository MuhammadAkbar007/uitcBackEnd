package web.uitc.uitcbackend.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CourseDto {

    @NotNull(message = "Course name can not be null !")
    private String name;

    @NotNull(message = "Course description can not be null !")
    private String description;
}
