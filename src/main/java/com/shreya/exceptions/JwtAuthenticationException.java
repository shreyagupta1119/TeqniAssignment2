package com.shreya.exceptions;

/**
 * Created by shreya on 25/1/17.
 */
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
}