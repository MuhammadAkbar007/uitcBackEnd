package web.uitc.uitcbackend.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "First name can not be null !")
    private String firstName;

    @NotNull(message = "Last name can not be null !")
    private String lastName;

    @NotNull(message = "Phone number can not be null !")
    private String phoneNumber;

    @NotNull(message = "Password can not be null !")
    private String password;
}
