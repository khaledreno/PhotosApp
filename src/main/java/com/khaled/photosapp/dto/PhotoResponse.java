package com.khaled.photosapp.dto;

import com.khaled.photosapp.entity.PhotoStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PhotoResponse {
    private Long id;
    private String location;
    private String uploaderName;
    private PhotoStatus status;
    private LocalDateTime createdAt;
}
