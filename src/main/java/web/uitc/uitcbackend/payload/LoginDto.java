package web.uitc.uitcbackend.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull(message = "Telefon raqam bo'sh bo'lmasligi kerak !")
    private String phoneNumber;

    @NotNull(message = "Parol bo'sh bo'lmasligi kerak !")
    private String password;
}
