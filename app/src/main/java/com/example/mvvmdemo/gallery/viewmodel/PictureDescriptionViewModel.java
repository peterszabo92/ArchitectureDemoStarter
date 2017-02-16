package com.example.mvvmdemo.gallery.viewmodel;


import com.example.mvvmdemo.data.datacontroller.ImageDataController;
import com.example.mvvmdemo.gallery.GalleryPageContract;

import io.reactivex.Observable;


public class PictureDescriptionViewModel implements GalleryPageContract.PictureDescriptionViewModel {

    private ImageDataController imageDataController;

    public PictureDescriptionViewModel(ImageDataController imageDataController) {
        this.imageDataController = imageDataController;
    }

    @Override
    public Observable<String> getImageDescription() {
        return imageDataController.getSelectedImageModel().flatMap(
                imageModel -> Observable.just(imageModel.getImageUrl()));
    }
}