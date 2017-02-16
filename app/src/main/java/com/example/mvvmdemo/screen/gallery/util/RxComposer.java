package com.example.mvvmdemo.screen.gallery.util;

import com.example.mvvmdemo.base.view.StateLayout;
import com.example.mvvmdemo.data.DataChecker;

import rx.Observable;


public interface RxComposer {

    <T> Observable.Transformer<T, T> setStateLayout(StateLayout stateLayout, DataChecker dataChecker);

}
