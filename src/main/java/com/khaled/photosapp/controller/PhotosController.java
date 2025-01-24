package com.khaled.photosapp.controller;


import com.khaled.photosapp.entity.PhotoEntity;
import com.khaled.photosapp.entity.PhotoStatus;
import com.khaled.photosapp.service.PhotoService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PhotosController {

    @Autowired
    private PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }


    @GetMapping("/pics")
    public List<PhotoEntity> images(){
        return photoService.ListAllPhotos();
    }

    @PutMapping("/pics")
    public void ChangePhotoStatus(@RequestBody PhotoEntity photoEntity){}

    @DeleteMapping("/pics")
    public String deleteImageById(@RequestParam Long id){
        photoService.deletePhoto(id);
        log.info("Photo deleted successfully");
        return "Photo deleted successfully";
    }




    @GetMapping("/user")
    public String NormalUser() {
        return "NormalUser";
    }










}
