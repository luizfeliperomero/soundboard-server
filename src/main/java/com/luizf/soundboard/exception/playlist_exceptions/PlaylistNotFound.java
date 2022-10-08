package com.luizf.soundboard.exception.playlist_exceptions;

public class PlaylistNotFound extends RuntimeException{
    public PlaylistNotFound(String message) {
        super(message);
    }
}
