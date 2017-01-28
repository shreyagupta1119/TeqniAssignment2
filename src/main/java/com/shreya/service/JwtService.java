package com.shreya.service;

import com.shreya.auth.SecretKeyProvider;
import com.shreya.dao.UserInfoDAO;
import com.shreya.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;

import static java.time.ZoneOffset.UTC;

/**
 * Created by shreya on 25/1/17.
 */
@Component
public class JwtService {
    private static final String ISSUER = "com.shreya";
    private SecretKeyProvider secretKeyProvider;

    @SuppressWarnings("unused")
    public JwtService() {
        this(null);
    }
    @Autowired
    public UserInfoDAO userInfoDAO;

    @Autowired
    public JwtService(SecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

    public String tokenFor(UserInfo userInfo) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Date expiration = Date.from(LocalDateTime.now().plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject(userInfo.getUsername())
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public UserInfo verify(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return userInfoDAO.findUserInfo(claims.getBody().getSubject().toString());
    }
}
