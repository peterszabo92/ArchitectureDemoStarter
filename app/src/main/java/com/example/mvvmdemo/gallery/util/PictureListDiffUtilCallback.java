package com.example.mvvmdemo.gallery.util;

import android.support.v7.util.DiffUtil;

import com.example.mvvmdemo.gallery.model.PictureListItem;
import com.example.mvvmdemo.base.model.ListItem;

import java.util.List;

public class PictureListDiffUtilCallback extends DiffUtil.Callback {


    private List<ListItem> oldList;
    private List<ListItem> newList;

    public PictureListDiffUtilCallback(List<ListItem> oldList, List<ListItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return ((PictureListItem) oldList.get(oldItemPosition)).getData().imageUrl.equals(
                ((PictureListItem) newList.get(newItemPosition)).getData().imageUrl);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return ((PictureListItem) oldList.get(oldItemPosition)).getData().imageUrl.equals(
                ((PictureListItem) newList.get(newItemPosition)).getData().imageUrl);
    }
}
