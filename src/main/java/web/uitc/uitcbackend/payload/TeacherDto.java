package web.uitc.uitcbackend.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TeacherDto {
    @NotNull(message = "First name can not be null !")
    private String firstName;

    @NotNull(message = "Last name can not be null !")
    private String lastName;

    @NotNull(message = "Stack can not be null !")
    private String stack;
}
