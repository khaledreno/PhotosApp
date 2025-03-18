package com.khaled.photosapp.controller;

import com.khaled.photosapp.service.PhotoService;
//import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.khaled.photosapp.entity.PhotoEntity;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
public class UploadController {

    private final PhotoService photoService;

    @Autowired
    public UploadController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
                                              @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        String uploadedBy = (userDetails != null) ? userDetails.getUsername() : "anonymous";

        // Save photo record first (without location)
        PhotoEntity savedPhoto = photoService.savePhoto("", uploadedBy);

        // Determine the upload directory
        String uploadDirectory = Paths.get(System.getProperty("user.dir"), "uploads").toString();
        Files.createDirectories(Paths.get(uploadDirectory)); // Ensure the directory exists

        // Generate filename
        String sanitizedFilename = sanitizeFilename(file.getOriginalFilename());
        String fileName = uploadedBy + "_" + sanitizedFilename;
        String imageLocation = Paths.get(uploadDirectory, fileName).toString();

        // Save file
        Files.write(Paths.get(imageLocation), file.getBytes());

        // Update the photo location in the database
        photoService.updatePhotoLocation(savedPhoto, imageLocation);

        log.info("Photo uploaded successfully at location: {}", imageLocation);
        return ResponseEntity.ok("Photo uploaded successfully by " + uploadedBy + "!");
    }



//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
//                                              @AuthenticationPrincipal UserDetails userDetails) throws IOException {
//        String uploadedBy = userDetails.getUsername(); // Get the currently logged-in user's username
//
//        // Sanitize filename and generate a timestamp
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//        String formattedDateTime = now.format(formatter);
//
//        String sanitizedFilename = sanitizeFilename(file.getOriginalFilename());
//        String fileName = uploadedBy + "_" + formattedDateTime + "_" + sanitizedFilename;
//
//        // First, save a photo entry in the database
//        PhotoEntity photoEntity = new PhotoEntity();
//
//        photoEntity.setUploaderName(uploadedBy);
//        photoEntity.setFilename(fileName);
//
//        // Save the photoEntity first to generate an ID
//        PhotoEntity savedPhoto = photoService.savePhotoEntity(photoEntity);
//
//        // Retrieve the dynamic location using the correct photo ID
//        String dynamicLocation = photoService.getPhotoLocation(savedPhoto);
//
//        // Ensure the directory exists
//        Path directoryPath = Paths.get(dynamicLocation);
//        if (!Files.exists(directoryPath)) {
//            Files.createDirectories(directoryPath);
//            log.info("Created uploads directory at {}", dynamicLocation);
//        }
//
//        // Save file in the dynamically determined location
//        String imageLocation = Paths.get(dynamicLocation, fileName).toString();
//        Files.write(Paths.get(imageLocation), file.getBytes());
//
//        // Update the database entry with the final storage location
//        savedPhoto.setLocation(imageLocation);
//        photoService.updatePhoto(savedPhoto);
//
//        log.info("Photo uploaded successfully at location: {}", imageLocation);
//
//        return ResponseEntity.ok("Photo uploaded successfully by " + uploadedBy + "!");
//    }


//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
//                                              @AuthenticationPrincipal UserDetails userDetails) throws IOException {
//        String uploadedBy = userDetails.getUsername(); // Get the currently logged-in user's username
//        if (uploadedBy == null) {
//            uploadedBy = "null";
//        }
//
//        // Generate a timestamp for the filename
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//        String formattedDateTime = now.format(formatter);
//
//        // Sanitize the filename to avoid unwanted characters
//        String sanitizedFilename = sanitizeFilename(file.getOriginalFilename());
//        String fileName = uploadedBy + "_" + formattedDateTime + "_" + sanitizedFilename;
//
//        // Create a PhotoEntity object and retrieve its storage location
//        PhotoEntity photoEntity = new PhotoEntity();
//        photoEntity.setUploaderName(uploadedBy);
//        String dynamicLocation = photoService.getPhotoLocation(photoEntity); // Retrieve path from DB
//
//
//        // Ensure the directory exists
//        Path directoryPath = Paths.get(dynamicLocation);
//        if (!Files.exists(directoryPath)) {
//            Files.createDirectories(directoryPath);
//            log.info("Created uploads directory at {}", dynamicLocation);
//        }
//
//        // Save file in the dynamically determined location
//        String imageLocation = Paths.get(dynamicLocation, fileName).toString();
//        Files.write(Paths.get(imageLocation), file.getBytes());
//
//        // Save the photo details in the database
//        photoService.savePhoto(imageLocation, uploadedBy);
//
//        log.info("Photo uploaded successfully at location: {}", imageLocation);
//
//        return ResponseEntity.ok("Photo uploaded successfully by " + uploadedBy + "!");
//    }

    private String determinePhotoLocation(PhotoEntity photo) {
        // You can modify this logic to dynamically determine the upload path
        return Paths.get(System.getProperty("user.dir"), "uploads").toString();
    }

    private String sanitizeFilename(String filename) {
        if (filename == null) {
            return "unnamed_file";
        }
        return filename.replaceAll("[^\\x00-\\x7F]", "_"); // Remove non-ASCII characters
    }
}
//@CrossOrigin(origins = "http://localhost:4200")
//@Slf4j
//@RestController
//public class UploadController {
//
//    private final PhotoService photoService;
//    private final String savedPhotosDirectory;
//
//    @Autowired
//    public UploadController(PhotoService photoService) {
//        this.photoService = photoService;
//        this.savedPhotosDirectory = Paths.get(System.getProperty("user.dir"), "uploads").toString();
//        createUploadsDirectory();
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
//                                              @AuthenticationPrincipal UserDetails userDetails) throws IOException {
//        String uploadedBy = userDetails.getUsername(); // Get the currently logged-in user's username
//
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        String formattedDateTime = now.format(formatter);
//
//        String sanitizedFilename = sanitizeFilename(file.getOriginalFilename());
//        String fileName = uploadedBy + "_" + sanitizedFilename;
//        String imageLocation = Paths.get(savedPhotosDirectory, fileName).toString();
//
//        log.info("Image location: {}", imageLocation);
//        log.info("Uploaded by: {}", uploadedBy);
//
//        Files.write(Paths.get(imageLocation), file.getBytes());
//        photoService.savePhoto(imageLocation, uploadedBy);
//
//        return ResponseEntity.ok("Photo uploaded successfully by " + uploadedBy + "!");
//    }
//
//    private void createUploadsDirectory() {
//        Path directoryPath = Paths.get(savedPhotosDirectory);
//        try {
//            if (!Files.exists(directoryPath)) {
//                Files.createDirectories(directoryPath);
//                log.info("Created uploads directory at {}", savedPhotosDirectory);
//            }
//        } catch (IOException e) {
//            log.error("Failed to create uploads directory", e);
//        }
//    }
//
//    private String sanitizeFilename(String filename) {
//        if (filename == null) {
//            return "unnamed_file";
//        }
//        return filename.replaceAll("[^\\x00-\\x7F]", "_");
//    }
//
//


//@CrossOrigin(origins = "http://localhost:4200") // Replace with your Angular app's URL
//@Slf4j
//@RestController
////@RequestMapping("/upload")
//public class UploadController {
//
//    @Autowired
//    private PhotoService photoService;
//
//    public UploadController(PhotoService photoService) {
//        this.photoService = photoService;
//    }
//
//    @SneakyThrows
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file,
//                                              @RequestParam("uploadedBy") String uploadedBy) throws IOException {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        String formattedDateTime = now.format(formatter);
//
//        String savedPhotosDirectory = "/Users/khaled/Desktop/fo/";
//        // String savedPhotosDirectory = "Khaled HD/Java Spring Projects/storageFolder/photos/";
//
//        // Sanitize the filename to remove non-ASCII characters
//        String originalFilename = file.getOriginalFilename();
//        String sanitizedFilename = sanitizeFilename(originalFilename);
//
//        // Append the uploader's name and sanitized filename
//        String fileName = uploadedBy + "_" + sanitizedFilename;
//
//        // Full directory + file name appended
//        String imageLocation = savedPhotosDirectory + fileName;
//
//        // Save the file into the desired directory
//        log.info("Image location is " + imageLocation);
//
//        // Ensure the directory exists
//        Path directoryPath = Paths.get(savedPhotosDirectory);
//        if (!Files.exists(directoryPath)) {
//            Files.createDirectories(directoryPath);
//        }
//
//        // Write the file to the specified location
//        Files.write(Path.of(imageLocation), file.getBytes());
//
//        // Save the metadata to the database
//        photoService.savePhoto(imageLocation, uploadedBy);
//
//        log.trace(uploadedBy);
//        log.trace("Photo uploaded successfully");
//        return ResponseEntity.ok("Photo uploaded!");
//    }
//
//    /**
//     * Sanitizes the filename by replacing non-ASCII characters with underscores.
//     *
//     * @param filename The original filename.
//     * @return The sanitized filename.
//     */
//    private String sanitizeFilename(String filename) {
//        if (filename == null) {
//            return "unnamed_file";
//        }
//        // Replace non-ASCII characters with underscores
//        return filename.replaceAll("[^\\x00-\\x7F]", "_");
//    }
//
//
//}