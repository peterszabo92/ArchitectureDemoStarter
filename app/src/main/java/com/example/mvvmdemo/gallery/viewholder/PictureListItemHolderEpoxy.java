package com.example.mvvmdemo.gallery.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.airbnb.epoxy.EpoxyHolder;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.util.AppConstants;
import com.example.mvvmdemo.util.DimensionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureListItemHolderEpoxy extends EpoxyHolder {

    @BindView(R.id.picture) public ImageView picture;

    @Override
    protected void bindView(View itemView) {
        ButterKnife.bind(this, itemView);
        setItemAspectRatio(itemView);
    }

    private void setItemAspectRatio(View itemView) {
        int margin = itemView.getResources().getDimensionPixelSize(R.dimen.picture_item_space);
        int cellWidth = (int) ((DimensionUtil.getScreenWidth(itemView.getContext()) * 0.4) - (2 * margin));
        int cellHeight = (int) (cellWidth * AppConstants.RATIO_916);

        FrameLayout.LayoutParams pictureParams = new FrameLayout.LayoutParams(cellWidth, cellHeight);
        RecyclerView.LayoutParams itemParams = new RecyclerView.LayoutParams(cellWidth, cellHeight);
        picture.setLayoutParams(pictureParams);
        itemView.setLayoutParams(itemParams);
    }
}
