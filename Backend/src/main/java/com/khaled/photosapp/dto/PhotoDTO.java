package com.khaled.photosapp.dto;

import com.khaled.photosapp.entity.PhotoStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PhotoDTO {
    private Long id;
    private String location;
    private String uploaderName;
    private String status;
    private LocalDateTime createdAt;
}
