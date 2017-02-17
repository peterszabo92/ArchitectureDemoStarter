package com.example.mvvmdemo.player.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mvvmdemo.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.util.Assertions;

@TargetApi(16)
public class DefaultExoPlayerView extends FrameLayout {

    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;

    private View surfaceView;
    private View shutterView;
    private DefaultPlayerControlView controller;
    private DefaultPlayerPreview preview;
    private AspectRatioFrameLayout contentFrame;

    private DefaultExoPlayerView.ComponentListener componentListener;
    private SimpleExoPlayer player;

    private int controllerShowTimeoutMs;
    private boolean useController;

    public DefaultExoPlayerView(Context context) {
        this(context, null);
    }

    public DefaultExoPlayerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultExoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater layoutInflater;

        int defaultPlayerLayoutId = R.layout.layout_default_player_view;
        int resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT;
        int controllerShowTimeoutMs = DefaultPlayerControlView.DEFAULT_SHOW_TIMEOUT_MS;

        int surfaceType = SURFACE_TYPE_SURFACE_VIEW;

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DefaultExoPlayerView, 0, 0);
            try {
                surfaceType = a.getInt(R.styleable.DefaultExoPlayerView_surface_type, surfaceType);
            } finally {
                a.recycle();
            }
        }

        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(defaultPlayerLayoutId, this);

        // Shutter view.
        shutterView = findViewById(R.id.player_shutter);

        // Content frame.
        contentFrame = (AspectRatioFrameLayout) findViewById(R.id.player_content_frame);
        setResizeMode(resizeMode);

        // Create a surface view and insert it into the content frame, if there is one.
        if (contentFrame != null && surfaceType != SURFACE_TYPE_NONE) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            surfaceView = surfaceType == SURFACE_TYPE_TEXTURE_VIEW ? new TextureView(context)
                    : new SurfaceView(context);
            surfaceView.setLayoutParams(params);
            contentFrame.addView(surfaceView, 0);
        } else {
            surfaceView = null;
        }

        // Preview layout.
        View previewPlaceHolder = findViewById(R.id.player_preview);
        if (previewPlaceHolder != null) {
            this.preview = new DefaultPlayerPreview(context, attrs);
            replacePlaceholder(previewPlaceHolder, preview);
        } else {
            this.preview = null;
        }

        // Player control view.
        View controllerPlaceholder = findViewById(R.id.player_controller_placeholder);
        if (controllerPlaceholder != null) {
            this.controller = new DefaultPlayerControlView(context, attrs);
            replacePlaceholder(controllerPlaceholder, controller);
        } else {
            this.controller = null;
        }

        componentListener = new DefaultExoPlayerView.ComponentListener();

        this.controllerShowTimeoutMs = controller != null ? controllerShowTimeoutMs : 0;
        this.useController = controller != null;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void replacePlaceholder(View placeHolder, View replacement) {
        replacement.setLayoutParams(placeHolder.getLayoutParams());
        ViewGroup parent = ((ViewGroup) placeHolder.getParent());
        int index = parent.indexOfChild(placeHolder);
        parent.removeView(placeHolder);
        parent.addView(replacement, index);
    }

    /**
     * Returns the player currently set on this view, or null if no player is set.
     */
    public SimpleExoPlayer getPlayer() {
        return player;
    }

    public void setPlayer(SimpleExoPlayer player) {
        // Return if the given player is already set
        if (this.player == player) {
            return;
        }
        // If there was a player, release it
        if (this.player != null) {
            this.player.setTextOutput(null);
            this.player.setVideoListener(null);
            this.player.removeListener(componentListener);
            this.player.setVideoSurface(null);
        }
        // Set the player
        this.player = player;
        // Give the player to the controller
        if (useController) {
            controller.setPlayer(player);
        }
        // Set the preview's visibility
        if (preview != null) {
            preview.setVisibility(VISIBLE);
        }

        if (player != null) {
            if (surfaceView instanceof TextureView) {
                player.setVideoTextureView((TextureView) surfaceView);
            } else if (surfaceView instanceof SurfaceView) {
                player.setVideoSurfaceView((SurfaceView) surfaceView);
            }
            player.setVideoListener(componentListener);
            player.addListener(componentListener);
            maybeShowController(false);
        } else {
            hideController();
        }
    }

    public DefaultPlayerPreview getPreview() {
        return preview;
    }

    /**
     * Sets the resize mode.
     *
     * @param resizeMode The resize mode.
     */
    public void setResizeMode(@AspectRatioFrameLayout.ResizeMode int resizeMode) {
        Assertions.checkState(contentFrame != null);
        contentFrame.setResizeMode(resizeMode);
    }

    private void maybeShowController(boolean isForced) {
        if (!useController || player == null) {
            return;
        }
        int playbackState = player.getPlaybackState();
        boolean showIndefinitely = playbackState == ExoPlayer.STATE_IDLE
                || playbackState == ExoPlayer.STATE_ENDED || !player.getPlayWhenReady();
        boolean wasShowingIndefinitely = controller.isVisible() && controller.getShowTimeoutMs() <= 0;
        controller.setShowTimeoutMs(showIndefinitely ? 0 : controllerShowTimeoutMs);
        if (isForced || showIndefinitely || wasShowingIndefinitely) {
            controller.show();
        }
    }

    /**
     * Set the {@link PlaybackControlView.VisibilityListener}.
     *
     * @param listener The listener to be notified about visibility changes.
     */
    public void setControllerVisibilityListener(PlaybackControlView.VisibilityListener listener) {
        Assertions.checkState(controller != null);
        controller.setVisibilityListener(listener);
    }

    /**
     * Shows the playback controls. Does nothing if playback controls are disabled.
     */
    public void showController() {
        if (useController) {
            maybeShowController(true);
        }
    }

    /**
     * Hides the playback controls. Does nothing if playback controls are disabled.
     */
    public void hideController() {
        if (controller != null) {
            controller.hide();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!useController || player == null || ev.getActionMasked() != MotionEvent.ACTION_DOWN) {
            return false;
        }
        if (controller.isVisible()) {
            controller.hide();
        } else {
            maybeShowController(true);
        }
        return true;
    }

    private final class ComponentListener implements SimpleExoPlayer.VideoListener, ExoPlayer.EventListener {

        // SimpleExoPlayer.VideoListener implementation

        @Override
        public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees,
                                       float pixelWidthHeightRatio) {
            if (contentFrame != null) {
                float aspectRatio = height == 0 ? 1 : (width * pixelWidthHeightRatio) / height;
                contentFrame.setAspectRatio(aspectRatio);

            }
        }

        @Override
        public void onRenderedFirstFrame() {
            if (shutterView != null) {
                shutterView.setVisibility(INVISIBLE);
            }
        }

        @Override
        public void onTracksChanged(TrackGroupArray tracks, TrackSelectionArray selections) {
            //updateForCurrentTrackSelections();
        }

        // ExoPlayer.EventListener implementation

        @Override
        public void onLoadingChanged(boolean isLoading) {
            preview.setVisibility(INVISIBLE);
            shutterView.setVisibility(isLoading ? VISIBLE : INVISIBLE);
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    preview.setVisibility(VISIBLE);
                    break;
                case ExoPlayer.STATE_READY:
                    maybeShowController(false);
                    break;
            }
        }

        @Override
        public void onPlayerError(ExoPlaybackException e) {
            // Do nothing.
        }

        @Override
        public void onPositionDiscontinuity() {
            // Do nothing.
        }

        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            // Do nothing.
        }

    }
}
