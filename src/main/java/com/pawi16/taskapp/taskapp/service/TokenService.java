package com.pawi16.taskapp.taskapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pawi16.taskapp.taskapp.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public String tokenize(User user){
        Algorithm algorithm = Algorithm();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expireAt = calendar.getTime();

        String token = JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(expireAt)
                .sign(algorithm);

        return token;
    }

    private Algorithm Algorithm() {
        return Algorithm.HMAC256(secret);
    }
}
