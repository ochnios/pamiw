package pl.ochnios.todobackend.dtos;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String surname;
    private String email;
    private String password;
}
