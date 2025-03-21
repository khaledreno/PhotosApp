package com.khaled.photosapp.Repos;

import com.khaled.photosapp.entity.User;
import com.khaled.photosapp.entity.UserRole;
import com.khaled.photosapp.entity.UserRolesValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.classfile.Interfaces;
import java.util.List;
import java.util.Optional;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
//    List<UserRole> findByUsername(String username);

//    List<UserRole> findByRole(UserRole userRole);

    Optional<UserRole> findByRole(UserRolesValues role);


//    UserRole findByRole(UserRolesValues urv);
//    User findUserByRole(UserRolesValues urv);
}