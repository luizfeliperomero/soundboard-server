package com.luizf.soundboard.user;

import com.luizf.soundboard.exception.UserNotFound;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
   private final UserRepository userRepository;

   public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
   }

   public User save(User user) {
      return this.userRepository.save(user);
   }

   public Optional<User> findByName(String name) {
      return userRepository.findUserByName(name);
   }

   public User authenticate(User user) {
     User u =  findByName(user.getName()).orElseThrow(() -> new UserNotFound("User not found"));
     if(u.getPassword().equals(user.getPassword())){
        return u;
     } else throw new UserNotFound("Wrong Password");
   }

}
