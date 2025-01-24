package com.khaled.photosapp.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class corsConfigurerClass implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/pics")
                .allowedOrigins("http://localhost:4200")  // Allow your frontend domain
                .allowedMethods("GET", "PUT", "DELETE")
                .allowCredentials(true);
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("/Users/khaled/Desktop/fo/");

    }
}


//package com.khaled.photosapp.configurations;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class corsConfigurerClass {
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/pics") // Match the /pics endpoint
//                        .allowedOrigins("http://localhost:4200") // Allow Angular frontend
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*")
//                        .allowCredentials(true);
//
//
////                registry.addMapping("/pics")
////                        .allowedOrigins("http://localhost:4200")
////                        .allowedMethods("GET", "POST", "PUT", "DELETE")
////                        .allowedHeaders("*")
////                        .allowCredentials(true);
////
////                registry.addMapping("/api/**")
////                        .allowedOrigins("http://localhost:4200")
////                        .allowedMethods("GET", "POST", "PUT", "DELETE")
////                        .allowedHeaders("*")
////                        .allowCredentials(true);
//            }
//        };
//    }
//
//}
