package com.example.mvvmdemo.data.datacontroller;

import com.example.mvvmdemo.base.datacontroller.DataController;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.data.usecase.GetGalleryPictures;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class ImageDataController implements DataController {

    private GetGalleryPictures getGalleryPictures;

    private BehaviorSubject<ImageModel> selectedImageModelObservable = BehaviorSubject.create();
    private BehaviorSubject<List<ImageModel>> currentImageModelListObservable = BehaviorSubject.create();
    private ImageModel selectedImageModel;
    private List<ImageModel> currentImageModelList;

    @Inject
    public ImageDataController(GetGalleryPictures getGalleryPictures) {
        this.getGalleryPictures = getGalleryPictures;
    }

    private void getImageModels() {
        getGalleryPictures.execute()
                .subscribeOn(Schedulers.io())
                .subscribe(imageModels -> {
                    currentImageModelList = imageModels;
                    if (selectedImageModel == null) {
                        selectedImageModel = imageModels.get(0);
                    }
                    notifySelectedImageModelChanged();
                    notifyCurrentImageModelListChanged();
                });
    }

    public Observable<ImageModel> getSelectedImageModel() {
        return selectedImageModelObservable;
    }

    public Observable<List<ImageModel>> getCurrentImageModelList() {
        if (currentImageModelList == null) {
            getImageModels();
        }
        return currentImageModelListObservable;
    }

    public void setSelectedImageModel(ImageModel imageModel) {
        selectedImageModel = imageModel;
        notifySelectedImageModelChanged();
    }

    private void notifyCurrentImageModelListChanged() {
        currentImageModelListObservable.onNext(currentImageModelList);
        currentImageModelListObservable.onCompleted();
    }

    private void notifySelectedImageModelChanged() {
        selectedImageModelObservable.onNext(selectedImageModel);
    }

    @Override
    public void init() {
        currentImageModelListObservable = BehaviorSubject.create();
    }

    @Override
    public void clearData() {
        currentImageModelList.clear();
        currentImageModelList = null;
    }
}
