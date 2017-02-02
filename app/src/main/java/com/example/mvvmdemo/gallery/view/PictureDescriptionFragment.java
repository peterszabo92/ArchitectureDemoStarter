package com.example.mvvmdemo.gallery.view;

import android.view.View;
import android.widget.TextView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseFragment;
import com.example.mvvmdemo.gallery.viewmodel.PictureDescriptionViewModel;

import butterknife.BindView;

public class PictureDescriptionFragment extends BaseFragment {

    @BindView(R.id.picture_description) TextView pictureDescription;

    private PictureDescriptionViewModel descriptionViewModel;

    @Override
    protected void init(View view) {
        descriptionViewModel = new PictureDescriptionViewModel(
                ((BaseApplication) getActivity().getApplication()).getAppComponent().getImageManager()
        );

        descriptionViewModel.getImageDescription().subscribe(description -> pictureDescription.setText(description));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_picture_description;
    }
}
