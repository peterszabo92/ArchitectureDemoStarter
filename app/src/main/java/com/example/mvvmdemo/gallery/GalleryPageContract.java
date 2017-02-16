package com.example.mvvmdemo.gallery;

import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.base.model.ViewModel;
import com.example.mvvmdemo.data.model.ImageModel;

import java.util.List;

import io.reactivex.Observable;


public interface GalleryPageContract {

    interface BigPictureViewModel extends ViewModel {
        Observable<ImageModel> getSelectedImage();
    }

    interface PictureListViewModel extends ViewModel {
        Observable<List<ListItem>> getPictureItems();

        void selectImage(ImageModel imageModel);
    }

    interface PictureDescriptionViewModel extends ViewModel {
        Observable<String> getImageDescription();
    }

}
