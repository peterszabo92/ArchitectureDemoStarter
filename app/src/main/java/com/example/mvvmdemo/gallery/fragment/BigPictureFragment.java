package com.example.mvvmdemo.gallery.fragment;

import android.view.View;
import android.widget.ImageView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseViewModelFragment;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.util.Logs;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class BigPictureFragment extends BaseViewModelFragment<GalleryPageContract.BigPictureViewModel> {

    @BindView(R.id.big_picture) ImageView bigPicture;

    @Override
    protected void init(View view) {
        compositeDisposable.add(
                viewModel.getSelectedImage()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(imageModel -> {
                            BaseApplication.imageLoader.loadSimpleImageFromUrl(bigPicture, imageModel.getImageUrl());
                        }, Logs::s));
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
