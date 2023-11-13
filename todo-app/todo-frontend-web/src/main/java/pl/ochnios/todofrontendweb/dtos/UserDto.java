package pl.ochnios.todofrontendweb.dtos;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String name;
    private String surname;
    private String fullname;
    private String email;
}
