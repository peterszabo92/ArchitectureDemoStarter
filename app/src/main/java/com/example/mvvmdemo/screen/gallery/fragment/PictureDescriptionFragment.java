package com.example.mvvmdemo.screen.gallery.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseViewModelFragment;
import com.example.mvvmdemo.screen.gallery.GalleryPageContract;
import com.example.mvvmdemo.screen.gallery.viewmodel.PictureDescriptionViewModel;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class PictureDescriptionFragment extends BaseViewModelFragment<GalleryPageContract.PictureDescriptionViewModel> {

    @BindView(R.id.picture_description) TextView pictureDescription;

    @Override
    protected void init(View view) {
        compositeDisposable.add(
                viewModel.getImageDescription()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(description -> pictureDescription.setText(description)));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_picture_description;
    }

    @Override
    protected GalleryPageContract.PictureDescriptionViewModel createViewModel() {
        return new PictureDescriptionViewModel(
                ((BaseApplication) getActivity().getApplication()).getAppComponent().getImageManager()
        );
    }
}
