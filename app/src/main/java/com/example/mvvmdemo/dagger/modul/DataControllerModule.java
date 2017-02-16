package com.example.mvvmdemo.dagger.modul;

import com.example.mvvmdemo.data.datacontroller.ImageDataController;
import com.example.mvvmdemo.data.usecase.GetGalleryPictures;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataControllerModule {

    @Singleton
    @Provides
    public ImageDataController provideImageManager(GetGalleryPictures getGalleryPictures) {
        return new ImageDataController(getGalleryPictures);
    }

}
