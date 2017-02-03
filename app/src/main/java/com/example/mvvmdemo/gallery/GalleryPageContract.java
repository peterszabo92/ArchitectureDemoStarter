package com.example.mvvmdemo.gallery;

import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.base.model.ViewModel;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.model.PictureListItemEpoxy;

import java.util.List;

import rx.Observable;

public interface GalleryPageContract {

    interface BigPictureViewModel extends ViewModel {
        Observable<ImageModel> getSelectedImage();
    }

    interface PictureListViewModel extends ViewModel {
        Observable<List<ListItem>> getPictureItems();

        Observable<List<PictureListItemEpoxy>> getPictureItemsEpoxy();

        void selectImage(ImageModel imageModel);
    }

    interface PictureDescriptionViewModel extends ViewModel {
        Observable<String> getImageDescription();
    }

}
