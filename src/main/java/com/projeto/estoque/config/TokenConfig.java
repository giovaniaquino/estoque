package com.projeto.estoque.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.projeto.estoque.model.UsuarioEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(UsuarioEntity usuario) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("userId", usuario.getId())
                .withSubject(usuario.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))// 24 horas
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUsuarioData> validarToken(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decode =   JWT.require(algorithm).build().verify(token);

            return Optional.of(new JWTUsuarioData(
                    decode.getClaim("userId").asLong(),
                    decode.getSubject()
            ));
        }catch (JWTVerificationException exception){
            return Optional.empty();
        }
    }
}
