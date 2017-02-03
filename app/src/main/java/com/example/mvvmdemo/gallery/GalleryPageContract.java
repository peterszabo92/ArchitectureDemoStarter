package com.example.mvvmdemo.gallery;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.base.model.ViewModel;
import com.example.mvvmdemo.data.model.ImageModel;

import java.util.List;

import rx.Observable;

public interface GalleryPageContract {

    interface BigPictureViewModel extends ViewModel {
        Observable<ImageModel> getSelectedImage();
        ImageModel getSelectedImageModel();
    }

    interface PictureListViewModel extends ViewModel {
        Observable<List<ListItem>> getPictureItems();

        void selectImage(ImageModel imageModel);
    }

    interface PictureDescriptionViewModel extends ViewModel {
        Observable<String> getImageDescription();
    }

}
