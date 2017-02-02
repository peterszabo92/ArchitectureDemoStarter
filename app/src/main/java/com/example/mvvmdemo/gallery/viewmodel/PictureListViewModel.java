package com.example.mvvmdemo.gallery.viewmodel;

import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.model.PictureListItem;
import com.example.mvvmdemo.base.model.ListItem;

import java.util.List;

import rx.Observable;

public class PictureListViewModel {

    private ImageManager imageManager;

    public PictureListViewModel(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    public Observable<List<ListItem>> getPictureItems() {
        return imageManager.getImageModels()
                .flatMapIterable(list -> list)
                .map(imageModel -> (ListItem) new PictureListItem(imageModel))
                .toList();
    }

    public void selectImage(ImageModel imageModel) {
        imageManager.setSelectedImageModel(imageModel);
    }

}
