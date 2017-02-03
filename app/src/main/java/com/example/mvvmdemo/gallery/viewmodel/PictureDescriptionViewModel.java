package com.example.mvvmdemo.gallery.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.data.model.ImageModel;

import rx.Observable;

public class PictureDescriptionViewModel extends BaseObservable {

    private ImageManager imageManager;
    public ObservableField<String> selectedImageUrl;

    public PictureDescriptionViewModel(ImageManager imageManager) {
        this.imageManager = imageManager;
        selectedImageUrl = new ObservableField<>();
        imageManager.getSelectedImageModel().subscribe(imageModel -> {
            selectedImageUrl.set(imageModel.imageUrl);
        });
    }

    public Observable<String> getImageDescription() {
        return imageManager.getSelectedImageModel().flatMap(imageModel -> Observable.just(imageModel.imageUrl));
    }

}
