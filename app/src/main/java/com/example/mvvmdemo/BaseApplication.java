package com.example.mvvmdemo;

import android.app.Application;

import com.example.mvvmdemo.component.AppComponent;
import com.example.mvvmdemo.component.DaggerAppComponent;
import com.example.mvvmdemo.modul.AppModule;
import com.example.mvvmdemo.util.DefaultImageLoader;
import com.example.mvvmdemo.util.DimensionUtil;
import com.example.mvvmdemo.util.ImageLoader;

public class BaseApplication extends Application {

    public static ImageLoader imageLoader;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        imageLoader = new DefaultImageLoader(getApplicationContext());
        appComponent = initAppComponent();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AppComponent initAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(getAppModule())
                .build();
    }

    public AppModule getAppModule() {
        return new AppModule(this);
    }

}

