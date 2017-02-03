package com.example.mvvmdemo.data.usecase;

import com.example.mvvmdemo.base.Usecase;
import com.example.mvvmdemo.data.ImageProvider;
import com.example.mvvmdemo.data.model.ImageModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetGalleryPictures extends Usecase<List<ImageModel>> {

    private ImageProvider imageProvider;

    @Inject
    public GetGalleryPictures(ImageProvider imageProvider) {
        this.imageProvider = imageProvider;
    }

    @Override
    public Observable<List<ImageModel>> execute() {
        return Observable.just(imageProvider.getImageModels());
    }
}
