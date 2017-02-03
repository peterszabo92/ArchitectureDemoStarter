package com.example.mvvmdemo.data.manager;

import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.data.usecase.GetGalleryPictures;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class ImageManager {

    private GetGalleryPictures getGalleryPictures;

    private PublishSubject<ImageModel> selectedImageModelObservable = PublishSubject.create();
    private ImageModel selectedImageModel;

    @Inject
    public ImageManager(GetGalleryPictures getGalleryPictures) {
        this.getGalleryPictures = getGalleryPictures;
    }

    public Observable<List<ImageModel>> getImageModels() {
        return getGalleryPictures.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(imageModels -> {
                    if (selectedImageModel == null && imageModels.size() > 0) {
                        selectedImageModel = imageModels.get(0);
                        notifySelectedImageModelChanged();
                    }
                    return Observable.just(imageModels);
                });
    }

    public Observable<ImageModel> getSelectedImageModel() {
        return selectedImageModelObservable;
    }

    public void setSelectedImageModel(ImageModel imageModel) {
        selectedImageModel = imageModel;
        notifySelectedImageModelChanged();
    }

    private void notifySelectedImageModelChanged() {
        selectedImageModelObservable.onNext(selectedImageModel);
    }

}
