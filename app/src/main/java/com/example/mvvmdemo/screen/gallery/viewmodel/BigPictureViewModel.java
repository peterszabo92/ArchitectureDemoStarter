package com.example.mvvmdemo.screen.gallery.viewmodel;

import com.example.mvvmdemo.data.datacontroller.ImageDataController;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.screen.gallery.GalleryPageContract;

import javax.inject.Inject;

import io.reactivex.Observable;


public class BigPictureViewModel implements GalleryPageContract.BigPictureViewModel {

    private ImageDataController imageDataController;

    @Inject
    public BigPictureViewModel(ImageDataController imageDataController) {
        this.imageDataController = imageDataController;
    }

    @Override
    public Observable<ImageModel> getSelectedImage() {
        return imageDataController.getSelectedImageModel();
    }
}