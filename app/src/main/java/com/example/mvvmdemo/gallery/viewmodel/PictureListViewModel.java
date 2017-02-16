package com.example.mvvmdemo.gallery.viewmodel;

import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.data.datacontroller.ImageDataController;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.gallery.model.PictureListItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public class PictureListViewModel implements GalleryPageContract.PictureListViewModel {

    private ImageDataController imageDataController;

    public PictureListViewModel(ImageDataController imageDataController) {
        this.imageDataController = imageDataController;
    }

    @Override
    public Observable<List<ListItem>> getPictureItems() {
        return imageDataController.getCurrentImageModelList()
                .flatMap(this::convertImageModelToListItem);
    }

    private Observable<List<ListItem>> convertImageModelToListItem(final List<ImageModel> imageModels) {
        List<ListItem> listItems = new ArrayList<>();
        for (int i = 0, size = imageModels.size(); i < size; i++) {
            listItems.add(new PictureListItem(imageModels.get(i)));
        }
        return Observable.just(listItems);
    }

    public Observable<List<ImageModel>> test() {
        return imageDataController.getCurrentImageModelList();
    }

    @Override
    public void selectImage(ImageModel imageModel) {
        imageDataController.setSelectedImageModel(imageModel);
    }

}