package com.example.mvvmdemo.gallery.viewmodel;

import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.base.model.ListItem;

import rx.Observable;

public class BigPictureViewModel {


    private ImageManager imageManager;

    public BigPictureViewModel(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    public Observable<ImageModel> getSelectedImage() {
        return imageManager.getSelectedImageModel();
    }
}
