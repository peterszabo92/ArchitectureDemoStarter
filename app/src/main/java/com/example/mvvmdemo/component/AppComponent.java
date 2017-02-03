package com.example.mvvmdemo.component;

import com.example.mvvmdemo.MainActivity;
import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.gallery.fragment.BigPictureFragment;
import com.example.mvvmdemo.gallery.viewmodel.BigPictureViewModel;
import com.example.mvvmdemo.modul.AppModule;
import com.example.mvvmdemo.modul.ManagerModule;
import com.example.mvvmdemo.modul.ProviderModule;
import com.example.mvvmdemo.modul.UsecaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ManagerModule.class, ProviderModule.class, UsecaseModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(BigPictureFragment bigPictureFragment);

    ImageManager getImageManager();

    BigPictureViewModel getBigPictureViewModel();

}
