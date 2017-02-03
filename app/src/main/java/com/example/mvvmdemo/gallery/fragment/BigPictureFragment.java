package com.example.mvvmdemo.gallery.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseViewModelFragment;
import com.example.mvvmdemo.databinding.FragmentBigPictureBinding;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.gallery.viewmodel.BigPictureViewModel;

public class BigPictureFragment extends BaseViewModelFragment<BigPictureViewModel> {

    @Override
    protected void init(View view) {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentBigPictureBinding pictureBinding = DataBindingUtil.bind(view);
        pictureBinding.setViewmodel(viewModel);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_big_picture;
    }

    @Override
    protected BigPictureViewModel createViewModel() {
        return ((BaseApplication) getActivity().getApplication()).getAppComponent().getBigPictureViewModel();
    }

}
