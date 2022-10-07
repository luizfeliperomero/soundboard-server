package com.luizf.soundboard.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user) {
       return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.save(user));
    }

    @PostMapping("/authenticate")
    public User authenticate(@RequestBody User user) {
        return  userService.authenticate(user);
    }

}
