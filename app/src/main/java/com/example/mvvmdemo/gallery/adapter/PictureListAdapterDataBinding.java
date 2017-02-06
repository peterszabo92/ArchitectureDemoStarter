package com.example.mvvmdemo.gallery.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mvvmdemo.base.BaseRecyclerViewAdapter;
import com.example.mvvmdemo.base.model.ListItem;
import com.example.mvvmdemo.gallery.util.PictureListDiffUtilCallback;
import com.example.mvvmdemo.gallery.viewholder.PictureListItemHolderDataBinding;

import java.util.List;


public class PictureListAdapterDataBinding extends BaseRecyclerViewAdapter<PictureListItemHolderDataBinding> {
    private LayoutInflater layoutInflater;

    @Override
    public PictureListItemHolderDataBinding onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new PictureListItemHolderDataBinding(binding);
    }

    @Override
    public void onBindViewHolder(PictureListItemHolderDataBinding holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(dataList.get(position).getData());
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
