package com.khaled.photosapp.controller;

import com.khaled.photosapp.service.PhotoService;
//import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") // Replace with your Angular app's URL
@Slf4j
@RestController
//@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private PhotoService photoService;

    public UploadController(PhotoService photoService) {
        this.photoService = photoService;
    }

//    @SneakyThrows
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
                                              @RequestParam("uploadedBy") String uploadedBy) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(formatter);

        String savedPhotosDirectory = "/Users/khaled/Desktop/fo/";
        // String savedPhotosDirectory = "Khaled HD/Java Spring Projects/storageFolder/photos/";

        // Sanitize the filename to remove non-ASCII characters
        String originalFilename = file.getOriginalFilename();
        String sanitizedFilename = sanitizeFilename(originalFilename);

        // Append the uploader's name and sanitized filename
        String fileName = uploadedBy + "_" + sanitizedFilename;

        // Full directory + file name appended
        String imageLocation = savedPhotosDirectory + fileName;

        // Save the file into the desired directory
        log.info("Image location is " + imageLocation);

        // Ensure the directory exists
        Path directoryPath = Paths.get(savedPhotosDirectory);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Write the file to the specified location
        Files.write(Path.of(imageLocation), file.getBytes());

        // Save the metadata to the database
        photoService.savePhoto(imageLocation, uploadedBy);

        log.trace(uploadedBy);
        log.trace("Photo uploaded successfully");
        return ResponseEntity.ok("Photo uploaded!");
    }

    /**
     * Sanitizes the filename by replacing non-ASCII characters with underscores.
     *
     * @param filename The original filename.
     * @return The sanitized filename.
     */
    private String sanitizeFilename(String filename) {
        if (filename == null) {
            return "unnamed_file";
        }
        // Replace non-ASCII characters with underscores
        return filename.replaceAll("[^\\x00-\\x7F]", "_");
    }


}