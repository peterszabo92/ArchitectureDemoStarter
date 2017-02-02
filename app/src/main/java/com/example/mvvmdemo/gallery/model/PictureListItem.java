package com.example.mvvmdemo.gallery.model;

import android.support.v7.widget.RecyclerView;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.viewholder.PictureListItemHolder;
import com.example.mvvmdemo.base.model.ListItem;

public class PictureListItem extends ListItem<ImageModel> {

    public PictureListItem(ImageModel imageModel) {
        data = imageModel;
    }

    @Override
    public void bind(RecyclerView.ViewHolder viewHolder, int position) {
        ((PictureListItemHolder) viewHolder).update(getData().imageUrl);
    }

    @Override
    public int getLayout() {
        return R.layout.item_picture_list;
    }

}
