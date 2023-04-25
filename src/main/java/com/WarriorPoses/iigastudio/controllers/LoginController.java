package com.WarriorPoses.iigastudio.controllers;

import com.WarriorPoses.iigastudio.models.user.User;
import com.WarriorPoses.iigastudio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser != null && passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return ResponseEntity.ok(dbUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
