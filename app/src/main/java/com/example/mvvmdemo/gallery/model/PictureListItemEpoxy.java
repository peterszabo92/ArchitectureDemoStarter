package com.example.mvvmdemo.gallery.model;

import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.viewholder.PictureListItemHolderEpoxy;

public class PictureListItemEpoxy extends EpoxyModelWithHolder<PictureListItemHolderEpoxy> {

    private ImageModel imageModel;

    public PictureListItemEpoxy(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_picture_list;
    }

    @Override
    protected PictureListItemHolderEpoxy createNewHolder() {
        return new PictureListItemHolderEpoxy();
    }

    @Override
    public void bind(PictureListItemHolderEpoxy holder) {
        BaseApplication.imageLoader.loadSimpleImageFromUrl(holder.picture, imageModel.imageUrl);
    }

}
