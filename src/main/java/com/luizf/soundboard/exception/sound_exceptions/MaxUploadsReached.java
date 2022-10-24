package com.luizf.soundboard.exception.sound_exceptions;

public class MaxUploadsReached extends RuntimeException{
    public MaxUploadsReached(String message) {
       super(message);
    }
}
