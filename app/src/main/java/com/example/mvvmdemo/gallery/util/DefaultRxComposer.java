package com.example.mvvmdemo.gallery.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.mvvmdemo.base.view.StateLayout;
import com.example.mvvmdemo.data.DataChecker;
import com.example.mvvmdemo.util.Logs;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public class DefaultRxComposer implements RxComposer  {

    private boolean wasError = false;

    @Override
    public <T> Observable.Transformer<T, T> setStateLayout(StateLayout stateLayout, @NonNull DataChecker dataChecker) {
        return observable -> observable
                .doOnSubscribe(() -> {
                    Logs.d("Start");
                    if (stateLayout != null) {
                        stateLayout.showLoading();
                    }
                })
                .doOnError(throwable -> {
                    wasError = true;
                    Logs.e("DefaultRxComposer.Error: ", throwable);
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (stateLayout != null) {
                            if (wasError) {
                                stateLayout.showError();
                            } else if (dataChecker.hasContent()) {
                                stateLayout.showContent();
                            } else {
                                stateLayout.showEmpty();
                            }
                        }
                        Logs.d("Action end!  wasError: " + wasError + " - hasContent: " + dataChecker.hasContent());
                    }
                });
    }


}
