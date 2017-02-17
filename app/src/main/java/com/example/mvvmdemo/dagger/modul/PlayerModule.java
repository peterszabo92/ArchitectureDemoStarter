package com.example.mvvmdemo.dagger.modul;

import android.content.Context;

import com.example.mvvmdemo.dagger.scope.ActivityScope;
import com.example.mvvmdemo.player.PlayerFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayerModule {

    @ActivityScope
    @Provides
    public PlayerFactory providePlayerFactory(Context context) {
        return new PlayerFactory(context);
    }

}
