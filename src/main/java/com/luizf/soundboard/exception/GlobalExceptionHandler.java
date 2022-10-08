package com.luizf.soundboard.exception;

import com.luizf.soundboard.exception.playlist_exceptions.PlaylistNotFound;
import com.luizf.soundboard.exception.sound_exceptions.SoundNotFound;
import com.luizf.soundboard.exception.user_exceptions.UserUnauthorized;
import com.luizf.soundboard.exception.user_exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UserNotFound.class})
    public ResponseEntity<Object> userNotFound(UserNotFound ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UserUnauthorized.class})
    public ResponseEntity<Object> userUnauthorized(UserUnauthorized ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage(), LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({PlaylistNotFound.class})
    public ResponseEntity<Object> playlistNotFound(PlaylistNotFound ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({SoundNotFound.class})
    public ResponseEntity<Object> soundNotFound(PlaylistNotFound ex) {
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }
}
