package com.khaled.photosapp.controller;


import com.khaled.photosapp.service.PhotoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RestController
public class PhotosController {

    private PhotoService  photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }



    @SneakyThrows //The @SneakyThrows annotation allows you to bypass the requirement to explicitly declare or handle checked exceptions in your methods.
    // When you annotate a method with @SneakyThrows, it silently throws any checked exceptions that would normally need to be caught or declared in a throws clause.

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
                                              @RequestParam("uploaddedBy") String uploaddedBy) throws IOException {

    String savedPhotosDirectory = "/Users/khaled/Desktop/fo/";
//    String savedPhotosDirectory = "Khaled HD/Java Spring Projects/storageFolder/photos/";

    String fileName = uploaddedBy+"_"+ file.getOriginalFilename();


//    if (fileName == null || !fileName.isEmpty()) {
//        return ResponseEntity.badRequest().body("File must have a name"); //exception to be handled
//    }

    //full directory + file name appended
    String ImageLocation = savedPhotosDirectory + fileName;

    //saving the file into the desired dicrectory
    log.info("image location is "+ImageLocation);

    Files.write(Path.of(ImageLocation),file.getBytes());

//save the metadata to DB
photoService.savePhoto(ImageLocation,uploaddedBy);

//String fileExtension = fileName.substring(fileName.lastIndexOf(".")); //to add certain extensions validation


log.trace(uploaddedBy);
log.trace("photo uploaded successfully");
        return ResponseEntity.ok("Photo uploaded!");

    }


    @GetMapping("/upload")
    public String Home() {
        return "upload a photo";
    }

    @GetMapping("/user")
    public String NormalUser() {
        return "NormalUser";
    }










}
