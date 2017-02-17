package com.example.mvvmdemo.dagger.component;

import com.example.mvvmdemo.dagger.modul.PlayerModule;
import com.example.mvvmdemo.dagger.modul.UtilModule;
import com.example.mvvmdemo.dagger.scope.ActivityScope;
import com.example.mvvmdemo.screen.menu.MenuFragment;
import com.example.mvvmdemo.screen.playerdemo.viewmodel.PlayerDemoViewModel;

import dagger.Component;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {UtilModule.class, PlayerModule.class})
public interface ActivityComponent {

    void inject(MenuFragment menuFragment);

    PlayerDemoViewModel getPlayerDemoViewModel();

}
