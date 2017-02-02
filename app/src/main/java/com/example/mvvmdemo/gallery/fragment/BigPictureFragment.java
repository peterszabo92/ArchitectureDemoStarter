package com.example.mvvmdemo.gallery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseFragment;
import com.example.mvvmdemo.data.manager.ImageManager;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.gallery.viewmodel.BigPictureViewModel;

import javax.inject.Inject;

import butterknife.BindView;

public class BigPictureFragment extends BaseFragment<GalleryPageContract.BigPictureViewModel> {

    @BindView(R.id.big_picture) ImageView bigPicture;

    @Override
    protected void init(View view) {
        viewModel.getSelectedImage()
                .subscribe(imageModel -> BaseApplication.imageLoader.loadSimpleImageFromUrl(
                        bigPicture, imageModel.imageUrl));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_big_picture;
    }

    @Override
    protected GalleryPageContract.BigPictureViewModel createViewModel() {
        return ((BaseApplication) getActivity().getApplication()).getAppComponent().getBigPictureViewModel();
    }

}
