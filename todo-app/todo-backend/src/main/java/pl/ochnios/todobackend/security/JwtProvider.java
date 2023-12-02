package pl.ochnios.todobackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@NoArgsConstructor
@Component
public class JwtProvider {

    private final static SecretKey pwd = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SecurityConsts.JWT_SECRET));

    public String generateToken(Authentication authentication) {
        String user = authentication.getName();
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + SecurityConsts.JWT_EXPIRATION);

        return Jwts.builder()
                .subject(user)
                .issuedAt(currentDate)
                .expiration(expirationDate)
                .signWith(pwd)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        return getJwsClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getJwsClaims(token);
            return true;
        } catch (Exception ex) {
            throw new AuthenticationCredentialsNotFoundException("Expired or incorrect token!");
        }
    }

    private Jws<Claims> getJwsClaims(String token) {
        return Jwts.parser()
                .verifyWith(pwd)
                .build()
                .parseSignedClaims(token);
    }
}
