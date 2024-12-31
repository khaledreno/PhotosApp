package com.khaled.photosapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotosController {

    @GetMapping("/")
    public String Home() {
        return "Homepage";
    }

    @GetMapping("/user")
    public String NormalUser() {
        return "NormalUser";
    }










}
