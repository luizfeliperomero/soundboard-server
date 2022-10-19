package com.luizf.soundboard.user;

import com.luizf.soundboard.exception.user_exceptions.UserUnauthorized;
import com.luizf.soundboard.exception.user_exceptions.UserNotFound;
import com.luizf.soundboard.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
   private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

   public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
       this.passwordEncoder = passwordEncoder;
       this.jwtUtil = jwtUtil;
       this.authenticationManager = authenticationManager;
   }

   public User save(User user) {
      if(userRepository.findUserByUsername(user.getUsername()).isPresent()) {
         throw new UserUnauthorized("Username " +user.getUsername()+ " already exists");
      }
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      return this.userRepository.save(user);
   }

   public Optional<User> findByUsername(String username) {
      return userRepository.findUserByUsername(username);
   }

   public AuthResponse authenticate(User user) {
       User u =  findByUsername(user.getUsername()).orElseThrow(() -> new UserNotFound("User not found"));
       if(passwordEncoder.matches(user.getPassword(), u.getPassword())){
           return new AuthResponse(u, jwtUtil.generateToken(user.getUsername()));
       } else throw new UserUnauthorized("Wrong Password");
   }

}
