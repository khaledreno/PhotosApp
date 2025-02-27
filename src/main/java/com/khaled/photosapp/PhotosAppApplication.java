package com.khaled.photosapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.khaled.photosapp")
public class PhotosAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotosAppApplication.class, args);
    }
}
