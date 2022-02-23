package web.uitc.uitcbackend.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;
}
