package com.example.mvvmdemo.gallery.viewmodel;

import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.data.datacontroller.ImageDataController;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.gallery.model.PictureListItem;

import java.util.List;

import rx.Observable;

public class PictureListViewModel implements GalleryPageContract.PictureListViewModel {

    private ImageDataController imageDataController;

    public PictureListViewModel(ImageDataController imageDataController) {
        this.imageDataController = imageDataController;
    }

    @Override
    public Observable<List<ListItem>> getPictureItems() {
        return imageDataController.getCurrentImageModelList()
                .flatMapIterable(list -> list)
                .map(PictureListItem::new)
                .cast(ListItem.class)
                .toList();
    }

    public Observable<List<ImageModel>> test() {
        return imageDataController.getCurrentImageModelList();
    }

    @Override
    public void selectImage(ImageModel imageModel) {
        imageDataController.setSelectedImageModel(imageModel);
    }

}
