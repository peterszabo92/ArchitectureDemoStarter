package com.example.mvvmdemo.player.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.mvvmdemo.R;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlaybackControlView;

public class DefaultPlayerControlView extends FrameLayout {

    /**
     * Listener to be notified about changes of the visibility of the UI control.
     */
    public interface VisibilityListener {

        /**
         * Called when the visibility changes.
         *
         * @param visibility The new visibility. Either {@link View#VISIBLE} or {@link View#GONE}.
         */
        void onVisibilityChange(int visibility);

    }

    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    private static final int PROGRESS_BAR_MAX = 1000;

    private ExoPlayer player;
    private PlaybackControlView.VisibilityListener visibilityListener;
    private DefaultPlayerControlView.ComponentListener componentListener;
    private final View stopButton;
    private final View volumeButton;
    private final View fullscreenButton;
    private final ProgressBar progressBar;

    private int showTimeoutMs;
    private long hideAtMs;
    private boolean isAttachedToWindow;

    private final Runnable updateProgressAction = this::updateProgress;

    private final Runnable hideAction = this::hide;

    public DefaultPlayerControlView(Context context) {
        this(context, null);
    }

    public DefaultPlayerControlView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultPlayerControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater layoutInflater;
        int defaultControllerLayoutId = R.layout.layout_default_player_control_view;

        componentListener = new DefaultPlayerControlView.ComponentListener();

        layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(defaultControllerLayoutId, this);
        setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);

        stopButton = findViewById(R.id.player_controls_stop);
        if (stopButton != null) {
            stopButton.setOnClickListener(componentListener);
        }
        volumeButton = findViewById(R.id.player_controls_volume);
        if (volumeButton != null) {
            volumeButton.setOnClickListener(componentListener);
        }
        fullscreenButton = findViewById(R.id.player_controls_fullscreen);
        if (fullscreenButton != null) {
            fullscreenButton.setOnClickListener(componentListener);
        }

        progressBar = (ProgressBar) findViewById(R.id.player_controls_progress);
        if (progressBar != null) {
            progressBar.setMax(PROGRESS_BAR_MAX);
            progressBar.setProgress(PROGRESS_BAR_MAX);
        }

    }

    /**
     * Returns the player currently being controlled by this view, or null if no player is set.
     */
    public ExoPlayer getPlayer() {
        return player;
    }

    /**
     * Sets the {@link ExoPlayer} to control.
     *
     * @param player the {@code ExoPlayer} to control.
     */
    public void setPlayer(ExoPlayer player) {
        if (this.player == player) {
            return;
        }
        if (this.player != null) {
            this.player.removeListener(componentListener);
        }
        this.player = player;
        if (player != null) {
            player.addListener(componentListener);
        }
        //updateAll();
    }

    /**
     * Returns the playback controls timeout. The playback controls are automatically hidden after
     * this duration of time has elapsed without user input.
     *
     * @return The duration in milliseconds. A non-positive value indicates that the controls will
     * remain visible indefinitely.
     */
    public int getShowTimeoutMs() {
        return showTimeoutMs;
    }

    /**
     * Sets the playback controls timeout. The playback controls are automatically hidden after this
     * duration of time has elapsed without user input.
     *
     * @param showTimeoutMs The duration in milliseconds. A non-positive value will cause the controls
     *                      to remain visible indefinitely.
     */
    public void setShowTimeoutMs(int showTimeoutMs) {
        this.showTimeoutMs = showTimeoutMs;
    }

    /**
     * Shows the playback controls. If {@link #getShowTimeoutMs()} is positive then the controls will
     * be automatically hidden after this duration of time has elapsed without user input.
     */
    public void show() {
        if (!isVisible()) {
            setVisibility(VISIBLE);
            if (visibilityListener != null) {
                visibilityListener.onVisibilityChange(getVisibility());
            }

            updateAll();
            /*
            requestPlayPauseFocus();
            */
        }
        // Call hideAfterTimeout even if already visible to reset the timeout.
        hideAfterTimeout();
    }

    /**
     * Hides the controller.
     */
    public void hide() {
        if (isVisible()) {
            setVisibility(GONE);
            if (visibilityListener != null) {
                visibilityListener.onVisibilityChange(getVisibility());
            }
            removeCallbacks(updateProgressAction);
            removeCallbacks(hideAction);
            hideAtMs = C.TIME_UNSET;
        }
    }

    public void stop() {

    }

    public void mute() {

    }

    /**
     * Sets the {@link PlaybackControlView.VisibilityListener}.
     *
     * @param listener The listener to be notified about visibility changes.
     */
    public void setVisibilityListener(PlaybackControlView.VisibilityListener listener) {
        this.visibilityListener = listener;
    }

    private void hideAfterTimeout() {
        removeCallbacks(hideAction);
        if (showTimeoutMs > 0) {
            hideAtMs = SystemClock.uptimeMillis() + showTimeoutMs;
            if (isAttachedToWindow) {
                postDelayed(hideAction, showTimeoutMs);
            }
        } else {
            hideAtMs = C.TIME_UNSET;
        }
    }

    private void updateAll() {
        /*
        updatePlayPauseButton();
        updateNavigation();
        updateProgress();
        */
    }

    private void updateProgress() {
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttachedToWindow = true;
        if (hideAtMs != C.TIME_UNSET) {
            long delayMs = hideAtMs - SystemClock.uptimeMillis();
            if (delayMs <= 0) {
                hide();
            } else {
                postDelayed(hideAction, delayMs);
            }
        }
        updateAll();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isAttachedToWindow = false;
        removeCallbacks(updateProgressAction);
        removeCallbacks(hideAction);
    }

    /**
     * Returns whether the controller is currently visible.
     */
    public boolean isVisible() {
        return getVisibility() == VISIBLE;
    }

    private final class ComponentListener implements ExoPlayer.EventListener, OnClickListener {

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            /*
            updatePlayPauseButton();
            updateProgress();
            */
        }

        @Override
        public void onPositionDiscontinuity() {
            /*
            updateNavigation();
            updateProgress();
            */
        }

        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {
            /*
            updateNavigation();
            updateProgress();
            */
        }

        @Override
        public void onLoadingChanged(boolean isLoading) {
            // Do nothing.
        }

        @Override
        public void onTracksChanged(TrackGroupArray tracks, TrackSelectionArray selections) {
            // Do nothing.
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            // Do nothing.
        }

        @Override
        public void onClick(View view) {
            if (player != null) {
                if (stopButton == view) {
                    Log.d("PlayerControlView", "stop botton clicked");
                    stop();
                } else if (volumeButton == view) {
                    Log.d("PlayerControlView", "mute botton clicked");
                    mute();
                } else if (fullscreenButton == view) {
                    Log.d("PlayerControlView", "fullscreen botton clicked");
                    //TODO fullscreen button click logic
                }
            }
            hideAfterTimeout();
        }

    }
}
