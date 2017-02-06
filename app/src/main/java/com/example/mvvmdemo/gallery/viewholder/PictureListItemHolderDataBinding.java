package com.example.mvvmdemo.gallery.viewholder;

import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.data.model.ImageModel;
import com.example.mvvmdemo.util.AppConstants;
import com.example.mvvmdemo.util.DimensionUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureListItemHolderDataBinding extends DefaultViewHolder {

    @BindView(R.id.picture)
    ImageView picture;

    public PictureListItemHolderDataBinding(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
        ButterKnife.bind(this, itemView);
        setItemAspectRatio(itemView);
    }

    @Override
    public void bind(Object obj) {
        super.bind(obj);
        BaseApplication.imageLoader.loadSimpleImageFromUrl(picture, ((ImageModel) obj).imageUrl);
    }

    private void setItemAspectRatio(View itemView) {
        int margin = itemView.getResources().getDimensionPixelSize(R.dimen.picture_item_space);
        int cellWidth = (int) ((DimensionUtil.getScreenWidth(itemView.getContext()) * 0.4) - (2 * margin));
        int cellHeight = (int) (cellWidth * AppConstants.RATIO_916);

        LinearLayout.LayoutParams pictureParams = new LinearLayout.LayoutParams(cellWidth, cellHeight);
        picture.setLayoutParams(pictureParams);
    }
}
