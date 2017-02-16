package com.example.mvvmdemo.data.model;

import android.support.annotation.NonNull;

public class ImageModel {

    @NonNull private String name;
    @NonNull private String imageUrl;

    public ImageModel(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
