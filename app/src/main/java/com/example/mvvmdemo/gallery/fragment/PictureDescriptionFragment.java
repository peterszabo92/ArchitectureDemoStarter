package com.example.mvvmdemo.gallery.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseViewModelFragment;
import com.example.mvvmdemo.databinding.FragmentPictureDescriptionBinding;
import com.example.mvvmdemo.gallery.viewmodel.PictureDescriptionViewModel;

public class PictureDescriptionFragment extends BaseViewModelFragment<PictureDescriptionViewModel> {

    @Override
    protected void init(View view) {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentPictureDescriptionBinding pictureDescriptionBinding = DataBindingUtil.bind(view);
        pictureDescriptionBinding.setViewmodel(viewModel);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_picture_description;
    }

    @Override
    protected PictureDescriptionViewModel createViewModel() {
        return new PictureDescriptionViewModel(
                ((BaseApplication) getActivity().getApplication()).getAppComponent().getImageManager()
        );
    }
}
