package com.WarriorPoses.iigastudio.controllers;



import com.WarriorPoses.iigastudio.models.user.User;
import com.WarriorPoses.iigastudio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public String showForm() {
        return "register";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody User user) {
        User newUser = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getFullName(), user.getUserRole());
        userRepository.save(newUser);

        return new ResponseEntity("Success", HttpStatus.OK);
    }

}
