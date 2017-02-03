package com.example.mvvmdemo.gallery.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.data.model.ImageModel;

import javax.inject.Inject;

public class BigPictureViewModel extends BaseObservable {

    private ImageManager imageManager;
    public ObservableField<String> selectedImageUrl;

    @Inject
    public BigPictureViewModel(ImageManager imageManager) {
        this.imageManager = imageManager;
        selectedImageUrl = new ObservableField<>();
        this.imageManager.getSelectedImageModel().subscribe(
                imageModel -> selectedImageUrl.set(imageModel.imageUrl));
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        BaseApplication.imageLoader.loadSimpleImageFromUrl(imageView, url);
    }

}
