package com.khaled.photosapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    private final Path fileStorageLocation = Paths.get("/Users/khaled/Desktop/fo").toAbsolutePath().normalize();

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        try {
            // Decode the file name to handle spaces (%20) and other special characters
            String decodedFileName = java.net.URLDecoder.decode(fileName, "UTF-8");

            // Resolve the file path using the full filesystem path
            Path filePath = this.fileStorageLocation.resolve(decodedFileName).normalize();
            log.info("Resolved file path: " + filePath); // Log the resolved file path

            Resource resource = new UrlResource(filePath.toUri());

            // Check if the file exists and is readable
            if (resource.exists() || resource.isReadable()) {
                log.info("File is readable: " + filePath); // Log that the file is readable
                // Determine the content type based on the file extension
                String contentType = getContentType(decodedFileName);

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType)) // Set the content type dynamically
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                log.error("File not found or not readable: " + filePath); // Log the error
                throw new RuntimeException("File not found or not readable: " + decodedFileName);
            }
        } catch (Exception e) {
            log.error("Failed to serve file: " + fileName); // Log the error
            throw new RuntimeException("Failed to serve file: " + fileName, e);
        }
    }

    // Helper method to determine the content type based on the file extension
    private String getContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "pdf":
                return "application/pdf";
            case "zip":
                return "application/zip";
            case "txt":
                return "text/plain";
            default:
                return "application/octet-stream"; // Default content type
        }
    }
    }


