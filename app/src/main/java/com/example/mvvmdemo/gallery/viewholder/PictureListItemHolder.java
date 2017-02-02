package com.example.mvvmdemo.gallery.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.util.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureListItemHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.picture)
    ImageView picture;

    public PictureListItemHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        setItemAspectRatio(itemView);
    }

    public void update(String pictureUrl) {
        BaseApplication.imageLoader.loadSimpleImageFromUrl(picture, pictureUrl);
    }

    private void setItemAspectRatio(View itemView) {
        int margin = itemView.getResources().getDimensionPixelSize(R.dimen.picture_item_space);
        int cellWidth = (int) ((BaseApplication.getDimensionUtil().getScreenWidth() * 0.4) - (2 * margin));
        int cellHeight = (int) (cellWidth * AppConstants.RATIO_916);

        FrameLayout.LayoutParams pictureParams = new FrameLayout.LayoutParams(cellWidth, cellHeight);
        RecyclerView.LayoutParams itemParams = new RecyclerView.LayoutParams(cellWidth, cellHeight);
        picture.setLayoutParams(pictureParams);
        itemView.setLayoutParams(itemParams);
    }
}
