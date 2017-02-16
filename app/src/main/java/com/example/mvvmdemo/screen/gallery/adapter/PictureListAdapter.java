package com.example.mvvmdemo.screen.gallery.adapter;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmdemo.base.BaseRecyclerViewAdapter;
import com.example.mvvmdemo.screen.gallery.util.PictureListDiffUtilCallback;
import com.example.mvvmdemo.screen.gallery.viewholder.PictureListItemHolder;
import com.example.mvvmdemo.base.model.ListItem;

import java.util.List;

public class PictureListAdapter extends BaseRecyclerViewAdapter {

    private LayoutInflater layoutInflater;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View itemView = layoutInflater.inflate(viewType, parent, false);

        return new PictureListItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        dataList.get(position).bind(holder, position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getLayout();
    }

    public void setDataList(List<ListItem> newDataList) {
        List<ListItem> oldList = this.dataList;
        this.dataList = newDataList;
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PictureListDiffUtilCallback(oldList, dataList));
        diffResult.dispatchUpdatesTo(this);
    }
}
