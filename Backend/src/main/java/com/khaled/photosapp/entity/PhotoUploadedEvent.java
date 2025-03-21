package com.khaled.photosapp.entity;

import lombok.Data;

@Data
public class PhotoUploadedEvent {

    private  String username;
    private  String photoUrl;

    public PhotoUploadedEvent(String username, String photoUrl) {
        this.username = username;
        this.photoUrl = photoUrl;
    }


}
