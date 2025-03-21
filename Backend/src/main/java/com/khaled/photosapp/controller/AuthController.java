package com.khaled.photosapp.controller;


import com.khaled.photosapp.dto.UserDTO;
import com.khaled.photosapp.entity.User;
import com.khaled.photosapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }


    //TODO:MAKE IT RETRUN USER NAME BSS
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.IsUserExist(user)) {
            log.info("User "+user.getUsername() +" already exists");
            return ResponseEntity.badRequest().body("User "+user.getUsername() +" already exists");
        } else {
            userService.saveUser(user);
            return ResponseEntity.ok("User " + user.toString() + "was created");
        }
    }



    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}", authentication);

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            log.warn("User is not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String currentUsername = authentication.getName();
        log.info("Current user: {}", currentUsername);

        User user = userService.findByUsername(currentUsername);
        if (user == null) {
            log.warn("User not found: {}", currentUsername);
            return ResponseEntity.notFound().build();
        }

        // To prevent returning the password in the response
        user.setPassword(null);

        return ResponseEntity.ok(user);
    }


}




