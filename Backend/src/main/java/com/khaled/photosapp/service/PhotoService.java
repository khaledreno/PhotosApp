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

//    @Transactional
//    public PhotoEntity savePhoto(String location, String uploaderName) {
//        if (location == null || location.isBlank() || uploaderName == null || uploaderName.isBlank()) {
//            throw new IllegalArgumentException("Location and uploader name must not be null or empty");
//        }
//
//        PhotoEntity photoObj = PhotoEntity.builder()
//                .location(location)
//                .uploaderName(uploaderName)
//                .status(PhotoStatus.PENDING)
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        // Save and log
//        PhotoEntity savedPhoto = photoRepository.save(photoObj);
//        log.info("Photo saved with id {}", savedPhoto.getId());
//        return savedPhoto;
//    }

    @Transactional
    public PhotoEntity savePhoto(String location, String uploaderName) {
        if (uploaderName == null || uploaderName.isBlank()) {
            throw new IllegalArgumentException("Uploader name must not be null or empty");
        }

        PhotoEntity photoObj = PhotoEntity.builder()
                .location(location != null ? location : "")
                .uploaderName(uploaderName)
                .status(PhotoStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        PhotoEntity savedPhoto = photoRepository.save(photoObj);
        log.info("Photo saved with id {}", savedPhoto.getId());
        return savedPhoto;
    }


    //return el location
    public String getPhotoLocation(PhotoEntity photoEntity) {
 return photoRepository.findById(photoEntity.getId())
         .map(PhotoEntity::getLocation)
         .orElseThrow(()-> new IllegalArgumentException("Photo not found"));}


    @Transactional
    public void updatePhotoLocation(PhotoEntity photo, String newLocation) {
        if (photo == null || newLocation == null || newLocation.isBlank()) {
            throw new IllegalArgumentException("Photo and new location must not be null or empty");
        }
        

        photo.setLocation(newLocation);
        photoRepository.save(photo); // Updates the database
        log.info("Updated photo location to {}", newLocation);
    }





//    public PhotoEntity updatePhotoStatus(PhotoEntity photoEntity) {
//        //retrive the object with id
//        PhotoEntity photoObj = photoRepository.findById(photoEntity.getId()).orElseThrow(() ->new IllegalArgumentException("Photo not found"));
//       photoObj.setStatus(photoEntity.getStatus());
////        PhotoEntity updatedPhoto = photoRepository.save(photoEntity);
//        log.info("Photo updated with id {} and new status is {}", photoObj.getId(), photoObj.getStatus());
//        photoRepository.save(photoObj);
//
//       // log.info("Photo updated with id {}", photoEntity.getId(), "new status is " + photoEntity.getStatus());
//        return photoObj;
//    }


    public PhotoEntity updatePhotoStatus(PhotoEntity photoEntity) {
        //make sure its there
        PhotoEntity photoObj = photoRepository.findById(photoEntity.getId()).orElseThrow(()-> new IllegalArgumentException("Photo not found"));
        photoObj.setStatus(photoEntity.getStatus());
        photoRepository.save(photoObj);
        log.info("Photo saved with id{} and new status is {}",photoObj.getId(), photoEntity.getStatus());
        return photoObj;
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
