package com.example.mvvmdemo.dagger.modul;

import android.content.Context;

import com.example.mvvmdemo.dagger.scope.ActivityScope;
import com.example.mvvmdemo.util.DimensionUtil;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {

    private boolean isTabletMode;

    public UtilModule(boolean isTabletMode) {
        this.isTabletMode = isTabletMode;
    }

    @ActivityScope
    @Provides
    DimensionUtil provideDimensionUtil(Context context) {
        return new DimensionUtil(context, isTabletMode);
    }

}