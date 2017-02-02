package com.example.mvvmdemo.data.manager;

import com.example.mvvmdemo.data.ImageProvider;
import com.example.mvvmdemo.data.model.ImageModel;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class ImageManager {

    private PublishSubject<ImageModel> selectedImageModelObservable = PublishSubject.create();
    private ImageModel selectedImageModel;

    public Observable<List<ImageModel>> getImageModels() {
        List<ImageModel> imageModels = ImageProvider.getImageModels();
        if (selectedImageModel == null && imageModels.size() > 0) {
            selectedImageModel = imageModels.get(0);
            notifySelectedImageModelChanged();
        }
        return Observable.just(imageModels);
    }

    public Observable<ImageModel> getSelectedImageModel() {
        return selectedImageModelObservable;
    }

    public void setSelectedImageModel(ImageModel imageModel) {
        selectedImageModel = imageModel;
        notifySelectedImageModelChanged();
    }

    public void notifySelectedImageModelChanged() {
        selectedImageModelObservable.onNext(selectedImageModel);
    }

}
