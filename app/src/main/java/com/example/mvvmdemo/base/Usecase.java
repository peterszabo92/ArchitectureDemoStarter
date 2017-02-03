package com.example.mvvmdemo.base;

import rx.Observable;

public abstract class Usecase<T> {

    public abstract Observable<T> execute();

}
