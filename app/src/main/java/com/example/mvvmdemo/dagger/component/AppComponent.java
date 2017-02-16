package com.example.mvvmdemo.dagger.component;

import android.content.Context;

import com.example.mvvmdemo.MainActivity;
import com.example.mvvmdemo.dagger.modul.AppModule;
import com.example.mvvmdemo.dagger.modul.DataControllerModule;
import com.example.mvvmdemo.dagger.modul.ProviderModule;
import com.example.mvvmdemo.dagger.modul.UsecaseModule;
import com.example.mvvmdemo.dagger.modul.UtilModule;
import com.example.mvvmdemo.data.datacontroller.ImageDataController;
import com.example.mvvmdemo.screen.gallery.fragment.BigPictureFragment;
import com.example.mvvmdemo.screen.gallery.viewmodel.BigPictureViewModel;
import com.example.mvvmdemo.screen.menu.MenuFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                DataControllerModule.class,
                ProviderModule.class,
                UsecaseModule.class,
        })
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(BigPictureFragment bigPictureFragment);

    Context getContext();

    ImageDataController getImageManager();

    BigPictureViewModel getBigPictureViewModel();

}
