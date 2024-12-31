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

//package com.khaled.photosapp.entity;
//
//import jakarta.persistence.*;
//import jdk.jfr.Enabled;
//import lombok.Builder;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//
//@Data
//@Builder
//@Entity
//@Getter
//@Setter
//@Table(name = "photoTable")
//public class PhotoEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "location",nullable = false)
//    private String location;
//
//    @Column(name = " status",nullable = false)
//    @Enumerated(EnumType.STRING)
//    private PhotoStatus status;
//
//
//    @Column(name = "creationDate")
//    private LocalDateTime createdAt;
//
//    @Column(name = "uploader")
//    private String uploaderName;
//
//    public PhotoEntity() {
//
//    }
//}
//
//
////
////enum PhotoStatus {
////    PENDING,
////    APPROVED,
////    DECLINED;
////}
//
//
//
