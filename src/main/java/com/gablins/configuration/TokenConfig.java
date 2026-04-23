package com.gablins.configuration;

import com.gablins.virtual_store.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class TokenConfig
{
    private String secret = "nada+eh+duradouro+tudo+eh+passageiro+memento+mori+aproveite+enquanto+pode";

    public String generateToken(User user)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        return Jwts.builder().
                claim("userId", user.getId()).
                setSubject(user.getUsername()).
                setExpiration(new Date(System.currentTimeMillis() + 8640000)).
                setIssuedAt(new Date()).
                signWith(signatureAlgorithm, secret).
                compact();
    }


    public Optional<JWTUserData> validateToken(String token)
    {
        try {
            Claims claims = Jwts.parserBuilder().
                    setSigningKey(secret).
                    build().
                    parseClaimsJws(token).
                    getBody();
            Long id = claims.get("userId", Long.class);

            return Optional.of(JWTUserData.builder().
                    userId(id).
                    email(claims.getSubject()).
                    build());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
