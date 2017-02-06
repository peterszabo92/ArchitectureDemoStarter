package com.example.mvvmdemo.gallery.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.example.mvvmdemo.BR;


public class DefaultViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding viewDataBinding;

    public DefaultViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
    }

    public void bind(Object obj) {
        viewDataBinding.setVariable(BR.obj, obj);
        viewDataBinding.executePendingBindings();
    }
}
