//package com.khaled.photosapp.service;
//
//import com.khaled.photosapp.listeners.AdminNotificationListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AdminService {
//
//    private final AdminNotificationListener notificationListener;
//
//    public AdminService(AdminNotificationListener notificationListener) {
//        this.notificationListener = notificationListener;
//    }
//
//    public void registerAdmin(String username) {
//        // Save admin to database logic here...
//        notificationListener.subscribeAdmin(username);
//        System.out.println("Admin " + username + " subscribed to photo notifications.");
//    }
//}