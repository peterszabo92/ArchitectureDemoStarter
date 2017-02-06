package com.example.mvvmdemo.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.util.ImageLoader;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final PublishSubject<ListItem> onClickSubject = PublishSubject.create();
    public ImageLoader imageLoader;
    protected LayoutInflater inflater;
    protected List<ListItem> dataList = Collections.emptyList();

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(dataList.get(position)));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public Observable<ListItem> getPositionClicks() {
        return onClickSubject.asObservable();
    }
}
