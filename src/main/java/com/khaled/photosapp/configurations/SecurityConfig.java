package com.khaled.photosapp.configurations;

import com.khaled.photosapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/pics/**").authenticated()
//                        .requestMatchers("/home").permitAll()
//                        .anyRequest().permitAll()
//                )
//                .formLogin(withDefaults()) // Use Spring's default oldlogiiin page
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/home")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//
//        return http.build();
//    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/pics/**").hasRole("ADMIN")
                        .requestMatchers("/home", "/login").permitAll()
                        .requestMatchers("/upload/**").hasRole("USER")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("http://localhost:4200/pics", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("http://localhost:4200/home")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .addFilterBefore((request, response, chain) -> {
                    HttpServletRequest httpRequest = (HttpServletRequest) request;
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    if (httpRequest.getRequestURI().equals("/login")) {
                        httpRequest.getSession().invalidate(); // Invalidate session
                    }
                    chain.doFilter(httpRequest, httpResponse);
                }, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
//Uses default spring boot oldlogiiin but working
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(withDefaults())
//                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/pics/**").authenticated()
//                        .requestMatchers("/pics/**").hasRole("ADMIN")
//                        .requestMatchers("/home","/oldlogiiin").permitAll()
//                        .requestMatchers("/upload/**").hasRole("USER")
//
//                                .anyRequest().permitAll()
//                )
//                .formLogin(form -> form
//                        .defaultSuccessUrl("http://localhost:4200/pics", true) // Redirect here after oldlogiiin
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // Custom logout endpoint
//                        .logoutSuccessUrl("http://localhost:4200/home") // Redirect after logout (optional)
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(withDefaults()) // This will pick up the global CORS config from `corsConfigurerClass`
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() // Adjust this later if you need more security
//                )
//                .formLogin(withDefaults()) // Enables Spring Default Login Page
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//
//        return http.build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


//    private final UserService userService;
//
//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(cors -> cors.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(List.of("http://localhost:4200"));
//                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//                    config.setAllowedHeaders(List.of("*"));
//                    config.setAllowCredentials(true);
//                    return config;
//                }))
//                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/oldlogiiin").permitAll()
////                        .requestMatchers("/pics").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .formLogin(AbstractHttpConfigurer::disable) // Disable Spring default form oldlogiiin
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
//
//        return http.build();
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(request -> {
//                    CorsConfiguration config = new CorsConfiguration();
//                    config.setAllowedOrigins(List.of("http://localhost:4200")); // Adjust if needed
//                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//                    config.setAllowedHeaders(List.of("*"));
//                    config.setAllowCredentials(true); // This is crucial
//                    return config;
//                }))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/pics").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .formLogin(withDefaults());
//
//        return http.build();
//    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(withDefaults())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/pics").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .httpBasic(withDefaults()) // Use HTTP Basic Auth (optional if you only rely on session)
//                .formLogin(AbstractHttpConfigurer::disable) // Disables the default oldlogiiin form
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint((request, response, authException) -> {
//                            response.setContentType("application/json");
//                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                            response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\"}");
//                        })
//                        .accessDeniedHandler((request, response, accessDeniedException) -> {
//                            response.setContentType("application/json");
//                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                            response.getWriter().write("{\"error\": \"Forbidden\", \"message\": \"" + accessDeniedException.getMessage() + "\"}");
//                        })
//                );
//
//        return http.build();
//    }


//wWORKINNNNNNNNNNNNNG
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final UserService userService;
//
//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(withDefaults()) // Enable CORS
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/pics").authenticated()
//                        .anyRequest().permitAll()
//                )
//                .formLogin(withDefaults());
//
//        return http.build();
//    }
//
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//package com.khaled.photosapp.configurations;
//
//import com.khaled.photosapp.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final UserService userService;
//
//    public SecurityConfig(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                                //.requestMatchers("/oldlogiiin/**").permitAll()  // Ensure authentication is required
////                        .requestMatchers("/auth/**").permitAll()
//                        .anyRequest().permitAll()
//                )
//                .formLogin(withDefaults());
//
//        return http.build();
//    }
//
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/", "/upload/**").permitAll()
////                        .requestMatchers("/pics/**").authenticated()
////                        .anyRequest().permitAll()
////                )
////                .formLogin(withDefaults());
////
////        return http.build();
////    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
