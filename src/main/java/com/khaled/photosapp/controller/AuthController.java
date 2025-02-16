package com.khaled.photosapp.controller;


import com.khaled.photosapp.dto.UserDTO;
import com.khaled.photosapp.dto.UserMapper;
import com.khaled.photosapp.entity.User;
import com.khaled.photosapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    //TODO:MAKE IT RETRUN USER NAME BSS
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.IsUserExist(user)) {
            return ResponseEntity.badRequest().body("User Aleready exsists!");
        } else {
            userService.saveUser(user);
            return ResponseEntity.ok("User " + user.toString() + "was created");
        }
    }



    @GetMapping("/login")
    public ResponseEntity<UserDTO> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String currentUsername = authentication.getName();

        try {
            User user = userService.findByUsername(currentUsername);
            UserDTO userDTO = UserMapper.INSTANCE.toDTO(user);
            return ResponseEntity.ok(userDTO);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    public class CurrentUser {
        public ResponseEntity<UserDTO> getCurrentUser() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
                User user = userService.findByUsername(authentication.getName());
                UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getCreatedAt(), user.getRole().getRole().name());
                return ResponseEntity.ok(userDTO);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }



//           @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        if (userService.findByUsername(user.getUsername()) != null) {
//            return ResponseEntity.badRequest().build();
//        }else return ResponseEntity.ok(userService.saveUser(user));
//    }
//


//    }  @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        if (userService.findByUsername(user.getUsername()).isPresent()) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(userService.saveUser(user));
//    }

//    @GetMapping("/login")
//    public ResponseEntity<User> getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentUsername = authentication.getName();
//        try {
//            User user = userService.findByUsername(currentUsername);
//            return ResponseEntity.ok(user);
//        } catch (UsernameNotFoundException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

}
