package com.study.rost.util;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;

@Getter
@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    //В токен можно помещать произвольное количество данных в виде k-v.
    public String generateToken(String username) {
        Date expirationTime = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("SecurityApplication")
                .withExpiresAt(expirationTime)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String JwtToken) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("SecurityApplication")
                .build();
        DecodedJWT jwt = verifier.verify(JwtToken);
        return jwt.getClaim("username").asString();
    }
}
