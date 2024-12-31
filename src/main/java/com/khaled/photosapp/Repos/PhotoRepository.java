package com.khaled.photosapp.Repos;

import com.khaled.photosapp.entity.PhotoEntity;
import com.khaled.photosapp.entity.PhotoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity,Long> {
    List<PhotoEntity> findByUploaderName(String uploadername);
    List<PhotoEntity> findByStatus(PhotoStatus status);



}
