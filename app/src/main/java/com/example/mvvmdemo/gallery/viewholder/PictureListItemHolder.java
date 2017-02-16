package com.example.mvvmdemo.gallery.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.util.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureListItemHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.picture_name)
    TextView pictureName;

    @BindView(R.id.picture)
    ImageView picture;

    @BindView(R.id.picture_container)
    FrameLayout pictureContainer;

    private int pictureWidth = -1;
    private int pictureHeight = -1;

    public PictureListItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        calcPreviewSize();
    }

    public void update(ImageModel imageModel) {
        setPreviewAspectRatio();
        pictureName.setText(imageModel.getName());
        BaseApplication.imageLoader.loadSimpleImageFromUrl(picture, imageModel.getImageUrl());
    }

    private void calcPreviewSize() {
        pictureContainer.post(() -> {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) pictureContainer.getLayoutParams();
            pictureWidth = pictureContainer.getWidth();
            pictureHeight = (int) (pictureWidth * AppConstants.RATIO_916);
            params.height = pictureHeight;
            pictureContainer.setLayoutParams(params);
        });
    }

    private void setPreviewAspectRatio() {
        pictureContainer.setLayoutParams(new LinearLayout.LayoutParams(pictureWidth, pictureHeight));
    }
}