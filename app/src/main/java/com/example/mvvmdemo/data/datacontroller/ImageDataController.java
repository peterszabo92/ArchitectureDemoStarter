package com.example.mvvmdemo.data.datacontroller;

import com.example.mvvmdemo.base.datacontroller.DataController;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.data.observable.ObservableValue;
import com.example.mvvmdemo.data.usecase.GetGalleryPictures;
import com.example.mvvmdemo.util.Logs;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import rx.schedulers.Schedulers;

public class ImageDataController implements DataController {

    private GetGalleryPictures getGalleryPictures;

    private ObservableValue<List<ImageModel>> currentImageModelList;
    private ObservableValue<ImageModel> selectedImageModel;
    private ObservableValue<Throwable> errorObservable;

    @Inject
    public ImageDataController(GetGalleryPictures getGalleryPictures) {
        this.getGalleryPictures = getGalleryPictures;
        currentImageModelList = new ObservableValue<>();
        selectedImageModel = new ObservableValue<>();
        errorObservable = new ObservableValue<>();
    }

    public void getImageModels() {
        Logs.d("ImageManage.getImageModels");
        getGalleryPictures.execute()
                .subscribeOn(Schedulers.io())
                .subscribe(imageModels -> {
                    currentImageModelList.setValue(imageModels);
                    if (selectedImageModel.getValue() == null) {
                        selectedImageModel.setValue(imageModels.get(0));
                    }
                }, errorObservable::setValue);
    }

    public Observable<Throwable> getErrors() {
        return errorObservable.getObservable();
    }

    public Observable<ImageModel> getSelectedImageModel() {
        return selectedImageModel.getObservable();
    }

    public void setSelectedImageModel(ImageModel imageModel) {
        selectedImageModel.setValue(imageModel);
    }

    public Observable<List<ImageModel>> getCurrentImageModelList() {
        if (currentImageModelList == null) {
            getImageModels();
        }
        return currentImageModelList.getObservable();
    }
}
