//package com.khaled.photosapp.listeners;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Slf4j
//public class NewUserSubListener {
//
//    private final Set<String> UserSubList = new HashSet<>();
//
//    public NewUserSubListener(String UserSubName) {
//        if (!UserSubList.contains(UserSubName)) {
//            UserSubList.add(UserSubName);
//            log.info("New User Sub Added: " + UserSubName);
//        }
//    }
//
//    public void RemoveUserSub(String UserSubName) {
//        if (UserSubList.contains(UserSubName)) {
//            UserSubList.remove(UserSubName);
//            log.info("Removed user sub: " + UserSubName);
//        }
//    }
//}
