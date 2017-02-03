package com.example.mvvmdemo.modul;

import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.data.usecase.GetGalleryPictures;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Singleton
    @Provides
    public ImageManager provideImageManager(GetGalleryPictures getGalleryPictures) {
        return new ImageManager(getGalleryPictures);
    }

}
