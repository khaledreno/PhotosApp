package com.khaled.photosapp.controller;

import com.khaled.photosapp.entity.PhotoEntity;
import com.khaled.photosapp.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.khaled.photosapp.entity.PhotoStatus.APPROVED;

@RestController
public class HomeController {

    private PhotoService photoService;

    @Autowired
    public HomeController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/home")
    public List<PhotoEntity> ApprovedPics(){

        return photoService.ListApprovedPhotos(APPROVED);
    }
}
