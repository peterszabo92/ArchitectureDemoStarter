package com.example.mvvmdemo.gallery.viewmodel;


import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.gallery.GalleryPageContract;

import rx.Observable;

public class PictureDescriptionViewModel implements GalleryPageContract.PictureDescriptionViewModel {

    private ImageManager imageManager;

    public PictureDescriptionViewModel(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    @Override
    public Observable<String> getImageDescription() {
        return imageManager.getSelectedImageModel().flatMap(imageModel -> Observable.just(imageModel.imageUrl));
    }
}
