package com.example.mvvmdemo.gallery.adapter;

import com.airbnb.epoxy.EpoxyAdapter;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.model.PictureListItemEpoxy;

import java.util.List;

public class PictureListAdapterEpoxy extends EpoxyAdapter {

    public PictureListAdapterEpoxy() {
        enableDiffing();
    }

    public void setDataList(List<PictureListItemEpoxy> pictureListItemEpoxies) {
        addModels(pictureListItemEpoxies);
        notifyModelsChanged();
    }
}
