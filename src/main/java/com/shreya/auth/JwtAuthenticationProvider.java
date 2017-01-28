package com.shreya.auth;

import com.shreya.exceptions.JwtAuthenticationException;
import com.shreya.model.UserInfo;
import com.shreya.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by shreya on 25/1/17.
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;

    @SuppressWarnings("unused")
    public JwtAuthenticationProvider() {
        this(null);
    }

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            UserInfo possibleUser = jwtService.verify((String) authentication.getCredentials());
                return new JwtAuthenticatedProfile(possibleUser);
        } catch (Exception e) {
            throw new JwtAuthenticationException("Failed to verify token", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}