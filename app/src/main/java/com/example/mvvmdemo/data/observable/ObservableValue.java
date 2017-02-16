package com.example.mvvmdemo.data.observable;

import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;


public class ObservableValue<T> {

    protected T value;
    protected BehaviorRelay<T> observable;

    public ObservableValue() {
        observable = BehaviorRelay.create();
    }

    public T getValue() {
        return value;
    }

    public Observable<T> getObservable() {
        return observable;
    }

    public void setValue(T value) {
        this.value = value;
        observable.accept(value);
    }

}