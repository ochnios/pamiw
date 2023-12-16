package pl.ochnios.todobackend.dtos;

import lombok.Data;
import pl.ochnios.todobackend.security.SecurityConsts;

@Data
public class AuthDto {
    private String accessToken;
    private String tokenType = SecurityConsts.TOKEN_TYPE;

    public AuthDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
