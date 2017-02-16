package com.example.mvvmdemo.dagger.component;

import com.example.mvvmdemo.dagger.modul.UtilModule;
import com.example.mvvmdemo.dagger.scope.ActivityScope;
import com.example.mvvmdemo.screen.menu.MenuFragment;


import dagger.Component;

@ActivityScope
@Component(dependencies = {AppComponent.class}, modules = {UtilModule.class})
public interface ActivityComponent {

    void inject(MenuFragment menuFragment);

}
