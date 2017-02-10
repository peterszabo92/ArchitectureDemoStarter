package com.example.mvvmdemo.component;

import com.example.mvvmdemo.MainActivity;
import com.example.mvvmdemo.data.datacontroller.ImageDataController;
import com.example.mvvmdemo.gallery.fragment.BigPictureFragment;
import com.example.mvvmdemo.gallery.viewmodel.BigPictureViewModel;
import com.example.mvvmdemo.modul.AppModule;
import com.example.mvvmdemo.modul.DataControllerModule;
import com.example.mvvmdemo.modul.ProviderModule;
import com.example.mvvmdemo.modul.UsecaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataControllerModule.class, ProviderModule.class, UsecaseModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(BigPictureFragment bigPictureFragment);

    ImageDataController getImageManager();

    BigPictureViewModel getBigPictureViewModel();

}
