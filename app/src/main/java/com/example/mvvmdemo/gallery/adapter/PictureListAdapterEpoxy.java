package com.example.mvvmdemo.gallery.adapter;

import android.util.Log;

import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.model.PictureListItemEpoxy;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public class PictureListAdapterEpoxy extends EpoxyAdapter {

    private final PublishSubject<ImageModel> onClickSubject = PublishSubject.create();

    public PictureListAdapterEpoxy() {
        enableDiffing();
    }

    public void setDataList(List<PictureListItemEpoxy> pictureListItemEpoxies) {
        addModels(pictureListItemEpoxies);
        notifyModelsChanged();
    }

    public Observable<ImageModel> onItemClick() {
        return onClickSubject.asObservable();
    }

    @Override
    public void onBindViewHolder(EpoxyViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(((PictureListItemEpoxy)models.get(position)).getImageModel()));
    }
}
