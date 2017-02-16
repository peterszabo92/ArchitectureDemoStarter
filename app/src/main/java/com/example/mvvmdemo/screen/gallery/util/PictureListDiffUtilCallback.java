package com.example.mvvmdemo.screen.gallery.util;

import android.support.v7.util.DiffUtil;

import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.screen.gallery.model.PictureListItem;

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
        return ((PictureListItem) oldList.get(oldItemPosition)).getData().getImageUrl().equals(
                ((PictureListItem) newList.get(newItemPosition)).getData().getImageUrl());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return ((PictureListItem) oldList.get(oldItemPosition)).getData().getImageUrl().equals(
                ((PictureListItem) newList.get(newItemPosition)).getData().getImageUrl());
    }
}
