package com.luizf.soundboard.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
}
