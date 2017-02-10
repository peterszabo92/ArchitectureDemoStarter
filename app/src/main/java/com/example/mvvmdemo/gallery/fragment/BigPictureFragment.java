package com.example.mvvmdemo.gallery.fragment;

import android.view.View;
import android.widget.ImageView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseViewModelFragment;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.gallery.GalleryPageContract;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BigPictureFragment extends BaseViewModelFragment<GalleryPageContract.BigPictureViewModel> {

    @BindView(R.id.big_picture) ImageView bigPicture;

    @Override
    protected void init(View view) {
        compositeSubscription.add(viewModel.getSelectedImage()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ImageModel>() {
                    @Override
                    public void call(ImageModel imageModel) {
                        BaseApplication.imageLoader.loadSimpleImageFromUrl(
                                bigPicture, imageModel.getImageUrl());
                    }
                }));
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
