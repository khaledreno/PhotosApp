package com.khaled.photosapp.service;

import com.khaled.photosapp.Repos.PhotoRepository;
import com.khaled.photosapp.entity.PhotoEntity;
import com.khaled.photosapp.entity.PhotoStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class PhotoService {

//save method takes address on local machine and uploader name
private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public PhotoEntity savePhoto(String location, String uploaderName) {
        if (location == null || location.isBlank() || uploaderName == null || uploaderName.isBlank()) {
            throw new IllegalArgumentException("Location and uploader name must not be null or empty");
        }

        PhotoEntity photoObj = PhotoEntity.builder()
                .location(location)
                .uploaderName(uploaderName)
                .status(PhotoStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        // Save and log
        PhotoEntity savedPhoto = photoRepository.save(photoObj);
      //  log.trace("Photo saved with id {}", savedPhoto.getId());
        return savedPhoto;
    }



    //which is better to send the status in the method or make an each method
    public List<PhotoEntity> ListApprovedPhotos(PhotoStatus status) {
        return photoRepository.findByStatus(status);

    }


    public List<PhotoEntity> findByUploaderName(String uploadername) {
        return photoRepository.findByUploaderName(uploadername);
    }
}
