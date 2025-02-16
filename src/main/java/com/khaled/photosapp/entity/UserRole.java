package com.khaled.photosapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

//TODO:FIX
@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    @Column(unique = true, nullable = false)
    private UserRolesValues role; // Use the enum instead of String

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL, orphanRemoval = false) //map to roles in user class
    private Set<User> users;



}

