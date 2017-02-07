package com.example.mvvmdemo.gallery.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.util.AppConstants;
import com.example.mvvmdemo.util.DimensionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureListItemHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.picture_name)
    TextView pictureName;

    @BindView(R.id.picture)
    ImageView picture;

    public PictureListItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        setItemAspectRatio(itemView);
    }

    public void update(ImageModel imageModel) {
        pictureName.setText(imageModel.getName());
        BaseApplication.imageLoader.loadSimpleImageFromUrl(picture, imageModel.getImageUrl());
    }

    private void setItemAspectRatio(View itemView) {
        int margin = itemView.getResources().getDimensionPixelSize(R.dimen.picture_item_space);
        int cellWidth = (int) ((DimensionUtil.getScreenWidth(itemView.getContext()) * 0.4) - (2 * margin));
        int cellHeight = (int) (cellWidth * AppConstants.RATIO_916);

        LinearLayout.LayoutParams pictureParams = new LinearLayout.LayoutParams(cellWidth, cellHeight);
        picture.setLayoutParams(pictureParams);
    }
}
