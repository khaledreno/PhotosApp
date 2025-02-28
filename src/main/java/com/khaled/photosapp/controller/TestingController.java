package com.khaled.photosapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestingController {

    @GetMapping("/pics/secret")
    public String secretPics() {
        return "This is a protected picture area. You're authenticated!";
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the home page. No oldlogiiin needed!";
    }
}
