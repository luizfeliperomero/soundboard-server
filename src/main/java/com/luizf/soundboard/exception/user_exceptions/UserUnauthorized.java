package com.luizf.soundboard.exception.user_exceptions;

public class UserUnauthorized extends RuntimeException {
    public UserUnauthorized(String message) {
       super(message);
    }
}
