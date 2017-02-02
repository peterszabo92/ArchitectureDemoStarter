package com.example.mvvmdemo.gallery.viewmodel;

import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.GalleryPageContract;

import rx.Observable;

public class BigPictureViewModel implements GalleryPageContract.BigPictureViewModel {

    private ImageManager imageManager;

    public BigPictureViewModel(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    @Override
    public Observable<ImageModel> getSelectedImage() {
        return imageManager.getSelectedImageModel();
    }
}
