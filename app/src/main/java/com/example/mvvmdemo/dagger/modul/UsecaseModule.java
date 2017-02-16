package com.example.mvvmdemo.dagger.modul;

import com.example.mvvmdemo.data.ImageProvider;
import com.example.mvvmdemo.data.usecase.GetGalleryPictures;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UsecaseModule {

    @Singleton
    @Provides
    public GetGalleryPictures provideGetGalleryPictures(ImageProvider imageProvider) {
        return new GetGalleryPictures(imageProvider);
    }

}
