package com.example.mvvmdemo.player.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.mvvmdemo.R;

public class DefaultPlayerPreview extends FrameLayout {

    private final ImageView previewImage;
    private final ImageView playButton;

    public DefaultPlayerPreview(Context context) {
        this(context, null);
    }

    public DefaultPlayerPreview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultPlayerPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater layoutInflater;
        int defaultPlayerLayoutId = R.layout.layout_player_preview;

        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(defaultPlayerLayoutId, this);

        previewImage = (ImageView) findViewById(R.id.player_preview_image);
        playButton = (ImageView) findViewById(R.id.player_preview_play_button);
    }

    public void setOnPlayClickListener(OnClickListener listener) {
        playButton.setOnClickListener(listener);
    }

    public ImageView getPreviewImage() {
        return previewImage;
    }
}
