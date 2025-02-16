package com.khaled.photosapp.Repos;

import com.khaled.photosapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo  extends JpaRepository<User, Integer> {
   Optional<User> findByUsername(String username);
   boolean existsByUsername(String username);

}
