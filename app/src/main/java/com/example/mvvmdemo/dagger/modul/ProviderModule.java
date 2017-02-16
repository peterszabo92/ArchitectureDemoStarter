package com.example.mvvmdemo.dagger.modul;

import com.example.mvvmdemo.data.ImageProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProviderModule {

    @Singleton
    @Provides
    public ImageProvider provideImageProvider() {
        return new ImageProvider();
    }


}
