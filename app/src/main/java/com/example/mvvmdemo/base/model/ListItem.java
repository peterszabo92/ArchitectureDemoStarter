package com.example.mvvmdemo.base.model;

import android.support.v7.widget.RecyclerView;

public abstract class ListItem<T> {

    protected T data;

    public abstract void bind(RecyclerView.ViewHolder viewHolder, int position);

    public abstract int getLayout();

    public T getData() {
        return data;
    }
}
