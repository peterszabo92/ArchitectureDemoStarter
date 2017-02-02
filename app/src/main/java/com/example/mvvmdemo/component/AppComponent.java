package com.example.mvvmdemo.component;

import com.example.mvvmdemo.MainActivity;
import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.modul.AppModule;
import com.example.mvvmdemo.modul.ManagerModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ManagerModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    ImageManager getImageManager();

}
