package com.example.mvvmdemo.gallery.viewmodel;


import com.example.mvvmdemo.data.manager.ImageManager;

import rx.Observable;

public class PictureDescriptionViewModel {

    private ImageManager imageManager;

    public PictureDescriptionViewModel(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    public Observable<String> getImageDescription() {
        return imageManager.getSelectedImageModel().flatMap(imageModel -> Observable.just(imageModel.imageUrl));
    }

}
