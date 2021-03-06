package com.shreya.exceptions;

/**
 * Created by shreya on 25/1/17.
 */
import static java.lang.String.format;

public class FailedToLoginException extends RuntimeException {
    public FailedToLoginException(String username) {
        super(format("Failed to login with username %s", username));
    }
}