package com.luizf.soundboard.exception.sound_exceptions;

public class SoundNotFound extends RuntimeException{
    public SoundNotFound(String message) {
       super(message);
    }
}
