package com.simple.base.project.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.simple.base.project.repository.ApplicationRepository;
import com.simple.base.project.repository.model.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class TokenService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public static final String TOKEN_SECRET = "s4T2zOIWHMM1sws";

    public String createToken(String userId, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("createdOn", new Date())
                    .sign(algorithm);
            Authentication auth = Authentication.builder()
                    .user(userId)
                    .password(password)
                    .token(token).build();
            applicationRepository.save(auth);
            return token;
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //log Token Signing Failed
        }
        return null;
    }

    public boolean isTokenValid(String token) {
        Authentication auth = applicationRepository.findFirstByToken(token);
        String userId = this.getUserIdFromToken(token);
        return userId != null && auth != null;
    }

    private String getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("userId").asString();
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }
}