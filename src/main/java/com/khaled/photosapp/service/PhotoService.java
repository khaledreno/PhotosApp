package com.khaled.photosapp.service;

import com.khaled.photosapp.Repos.PhotoRepository;
import com.khaled.photosapp.entity.PhotoEntity;
import com.khaled.photosapp.entity.PhotoStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
public class PhotoService {

//save method takes address on local machine and uploader name
    @Autowired
private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Transactional
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
        log.info("Photo saved with id {}", savedPhoto.getId());
        return savedPhoto;
    }



    //which is better to send the status in the method or make an each method
    public List<PhotoEntity> ListApprovedPhotos(PhotoStatus status) {
        return photoRepository.findByStatus(status);

    }

    public List<PhotoEntity> ListAllPhotos() {
        return photoRepository.findAll();
    }


    public List<PhotoEntity> findByUploaderName(String uploadername) {
        return photoRepository.findByUploaderName(uploadername);
    }

    public String deletePhoto(Long photoId) {
        if (CheckIfPhotoExists(photoId)) {
            photoRepository.deleteById(photoId);
            return "Photo deleted with id " + photoId;
        }
        throw new IllegalArgumentException("Photo with id " + photoId + " not found");
    }

    public boolean CheckIfPhotoExists(Long photoId) {
        return photoRepository.existsById(photoId);
    }
}
