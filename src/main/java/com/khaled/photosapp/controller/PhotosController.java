package com.khaled.photosapp.controller;


import com.khaled.photosapp.dto.PhotoDTO;
import com.khaled.photosapp.dto.PhotoMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PhotosController {

    @Autowired
    private PhotoService photoService;

    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    //TODO all responses are null
    @GetMapping("/pics")
    public ResponseEntity<List<PhotoDTO>> getAllPhotos (){
    List<PhotoEntity> photoEntityObjs = photoService.ListAllPhotos(); //retrive from repo via service layer
        List<PhotoDTO> photoObj = photoEntityObjs.stream()  //convert from entityobj to DTO object via toDto method and used stream to loop on all items
                .map(PhotoMapper.INSTANCE::toDTO)  //loop every element to the toDTO method via map
                .collect(Collectors.toList()); //collect all new objects into new photoObj

        return ResponseEntity.ok(photoObj);
    }


//    @GetMapping("/pics")
//    public List<PhotoEntity> images(){
//        return photoService.ListAllPhotos();
//    }

    @PutMapping("/pics")
    public ResponseEntity<Map<String, String>> ChangePhotoStatus(@RequestBody PhotoDTO photoDTOobj) {
        log.info("object received: " + photoDTOobj.toString());
        if (photoDTOobj.getId() == null)
            throw new IllegalArgumentException("id is null");

        PhotoEntity photoEntityobj = PhotoMapper.INSTANCE.toEntity(photoDTOobj);
        photoService.updatePhotoStatus(photoEntityobj);
        log.info("Changed status of photo {}", photoEntityobj.getId());

        // Return a JSON response
        Map<String, String> response = new HashMap<>();
        response.put("message", "Changed status of photo with id " + photoEntityobj.getId() + " to " + photoEntityobj.getStatus());
        return ResponseEntity.ok(response);}

//    //gayli DTO h7wlo el entity 3shan a3mlo update bl service
//    @PutMapping("/pics")
//    public ResponseEntity<String> ChangePhotoStatus(@RequestBody PhotoDTO photoDTOobj) {
//        log.info("object recived "+photoDTOobj.toString());
//        if (photoDTOobj.getId()==null)
//            throw new IllegalArgumentException("id is null");
//
//        PhotoEntity photoEntityobj = PhotoMapper.INSTANCE.toEntity(photoDTOobj);
//        photoService.updatePhotoStatus(photoEntityobj);
//        log.info("Changed status of photo {}", photoEntityobj.getId());
//        //return "Changed status of photo with id " + photoEntityobj.getId()+" to "+photoEntityobj.getStatus();
//        return ResponseEntity.ok("Sucess");
//    }

//    @PutMapping("/pics")
//    public void ChangePhotoStatus(@RequestBody PhotoEntity photoDTOobj) {
//       // PhotoEntity photoEntityobj = PhotoMapper.INSTANCE.toEntity(photoDTOobj);
//        photoService.updatePhotoStatus(photoDTOobj);
//    }




    @DeleteMapping("/pics")
    public ResponseEntity<Map<String, String>> deleteImageById(@RequestParam Long id){
        photoService.deletePhoto(id);
        log.info("Photo deleted successfully");

        Map<String, String> response = new HashMap<>();
        response.put("message", "Deleted photo with id " + id + " successfully");
        return ResponseEntity.ok(response);}


//    @DeleteMapping("/pics")
//    public String deleteImageById(@RequestParam Long id){
//        photoService.deletePhoto(id);
//        log.info("Photo deleted successfully");
//        return "Photo deleted successfully";
//    }




    @GetMapping("/user")
    public String NormalUser() {
        return "NormalUser";
    }










}
