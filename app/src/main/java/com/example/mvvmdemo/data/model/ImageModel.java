package com.example.mvvmdemo.data.model;

public class ImageModel {

    private String name;
    private String imageUrl;

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
