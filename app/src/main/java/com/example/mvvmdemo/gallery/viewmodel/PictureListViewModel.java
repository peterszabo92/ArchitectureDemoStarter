package com.example.mvvmdemo.gallery.viewmodel;

import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.gallery.model.PictureListItem;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class PictureListViewModel implements GalleryPageContract.PictureListViewModel {

    private ImageManager imageManager;

    public PictureListViewModel(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    @Override
    public Observable<List<ListItem>> getPictureItems() {
        return imageManager.getImageModels()
                .flatMapIterable(list -> list)
                .map(PictureListItem::new)
                .cast(ListItem.class)
                .toList();
    }

    @Override
    public void selectImage(ImageModel imageModel) {
        imageManager.setSelectedImageModel(imageModel);
    }

}
