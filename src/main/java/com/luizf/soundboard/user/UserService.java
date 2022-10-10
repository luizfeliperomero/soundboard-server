package com.luizf.soundboard.user;

import com.luizf.soundboard.exception.user_exceptions.UserUnauthorized;
import com.luizf.soundboard.exception.user_exceptions.UserNotFound;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
   private final UserRepository userRepository;

   public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
   }

   public User save(User user) {
      if(userRepository.findUserByUsername(user.getUsername()).isPresent()) {
         throw new UserUnauthorized("Username " +user.getUsername()+ " already exists");
      }
      return this.userRepository.save(user);
   }

   public Optional<User> findByUsername(String username) {
      return userRepository.findUserByUsername(username);
   }

   public User authenticate(User user) {
     User u =  findByUsername(user.getUsername()).orElseThrow(() -> new UserNotFound("User not found"));
     if(u.getPassword().equals(user.getPassword())){
        return u;
     } else throw new UserUnauthorized("Wrong Password");
   }

}
