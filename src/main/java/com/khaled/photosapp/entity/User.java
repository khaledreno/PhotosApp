package com.khaled.photosapp.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;


//TODO:FIX

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name ="role_id",nullable = true)
    private UserRole role;

    @CreationTimestamp // Automatically sets the current timestamp when inserted
    @Column(name = "created_at",updatable = true)
    private LocalDateTime createdAt;

    }



