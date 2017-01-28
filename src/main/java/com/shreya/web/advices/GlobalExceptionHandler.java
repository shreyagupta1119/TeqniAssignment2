package com.shreya.web.advices;

import com.shreya.exceptions.FailedToLoginException;
import com.shreya.exceptions.ProfileNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.SignatureException;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by shreya on 23/1/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ProfileNotFoundException.class)
    public void profileNotFound() {
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(FailedToLoginException.class)
    public void failedToLogin() {
    }

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(SignatureException.class)
    public void failedToVerify() {
        System.out.println("");
    }
}
