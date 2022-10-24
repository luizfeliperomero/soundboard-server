package com.luizf.soundboard.exception.playlist_exceptions;

public class MaxPlaylists extends RuntimeException{
    public MaxPlaylists(String message) {
       super(message);
    }
}
