package com.example.mvvmdemo.gallery.view;

import android.view.View;
import android.widget.ImageView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseFragment;
import com.example.mvvmdemo.base.model.ViewModel;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.gallery.viewmodel.BigPictureViewModel;

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
        return new BigPictureViewModel(
                ((BaseApplication) getActivity().getApplication()).getAppComponent().getImageManager()
        );
    }


}
