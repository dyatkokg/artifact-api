package me.dyatkokg.artefactapi.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import me.dyatkokg.artefactapi.entity.User;
import me.dyatkokg.artefactapi.service.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenProviderImplementation implements TokenProvider {

    @Value("${token.signature}")
    private String signature;

    @Override
    public String generateToken(User user) {
        Date currentDate = new Date();
        Date expireAt = new Date(currentDate.getTime() + (30 * 6000000));

        return JWT.create()
                .withIssuedAt(currentDate)
                .withExpiresAt(expireAt)
                .withIssuer("artifact-app")
                .withSubject(user.getId().toString())
                .withJWTId(UUID.randomUUID().toString())
                .sign(Algorithm.HMAC256(signature));
    }

}