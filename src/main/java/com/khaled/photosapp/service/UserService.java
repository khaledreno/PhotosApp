package com.khaled.photosapp.service;


import com.khaled.photosapp.Repos.UsersRepo;
import com.khaled.photosapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UsersRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersRepo userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User name not found "+username));
        return user;
    }

    public boolean IsUserExist(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User name not found "+username));
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
        User user = findByUsername(username);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}