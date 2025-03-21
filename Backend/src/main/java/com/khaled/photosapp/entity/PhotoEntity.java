package com.khaled.photosapp.entity; // Add this at the top of the file

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor // Add this to satisfy JPA's requirement for a no-arg constructor
@Entity
@Table(name = "photos")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String uploaderName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotoStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
