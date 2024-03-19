package com.ezbudget.EzBudgetAPI.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ezbudget.EzBudgetAPI.model.UserAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${spring.application.name}")
    private String issuer;

    public String generateToken(UserAuth user) {
        try {
            Algorithm alg = Algorithm.HMAC256(secret);

            return JWT.create().withIssuer(issuer)
                    .withSubject(user.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.of("-03:00"))).sign(alg);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Error: Could not generate JWT Token");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm alg = Algorithm.HMAC256(secret);
            return JWT.require(alg).withIssuer(issuer).build().verify(token).getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

}
