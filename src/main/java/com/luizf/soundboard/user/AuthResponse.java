package com.luizf.soundboard.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    User user;
    String jwt;
}
