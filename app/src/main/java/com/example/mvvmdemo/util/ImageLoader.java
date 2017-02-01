package com.example.mvvmdemo.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public interface ImageLoader {

    void loadCircularImageFromUrl(ImageView imageView, String url, Drawable fallbackImage);

    void loadSimpleImageFromUrl(ImageView imageView, String url);

}
