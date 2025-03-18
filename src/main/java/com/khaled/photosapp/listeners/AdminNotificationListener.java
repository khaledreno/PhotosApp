//package com.khaled.photosapp.listeners;
//
//import com.khaled.photosapp.entity.PhotoUploadedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AdminNotificationListener {
//
//    @EventListener
//    public void onPhotoUploaded(PhotoUploadedEvent event) {
//        System.out.println("New photo uploaded by: " + event.getUsername() + " | Photo URL: " + event.getPhotoUrl());
//        // Logic to notify admin (e.g., send an email, push notification, or update a WebSocket)
//    }
//}