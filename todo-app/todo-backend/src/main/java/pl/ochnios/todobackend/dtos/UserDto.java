package pl.ochnios.todobackend.dtos;

import lombok.Data;
import pl.ochnios.todobackend.models.User;

@Data
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String email;

    public static UserDto mapToDto(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.id = user.getId();
        dto.name = user.getName();
        dto.surname = user.getSurname();
        dto.email = user.getEmail();

        return dto;
    }
}
