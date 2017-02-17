package com.example.mvvmdemo.player;

import android.content.Context;

import com.example.mvvmdemo.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;


public class PlayerFactory {

    private Context context;
    private String userAgent;

    public PlayerFactory(Context context) {
        this.context = context;
        userAgent = Util.getUserAgent(this.context, context.getString(R.string.app_name));
    }

    public SimpleExoPlayer createSimplePlayer() {
        return createSimplePlayer(
                context,
                getDefaultTrackSelector(getDefaultTrackSelectionFactory(getDefaultBandwidthMeter())),
                getDefaultLoadControl());
    }

    public SimpleExoPlayer createSimplePlayer(Context context, TrackSelector trackSelector, LoadControl loadControl) {
        return com.google.android.exoplayer2.ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
    }

    private BandwidthMeter getDefaultBandwidthMeter() {
        return new DefaultBandwidthMeter();
    }

    private TrackSelection.Factory getDefaultTrackSelectionFactory(BandwidthMeter bandwidthMeter) {
        return new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
    }

    private TrackSelector getDefaultTrackSelector(TrackSelection.Factory videoTrackSelectionFactory) {
        return new DefaultTrackSelector(videoTrackSelectionFactory);
    }

    private LoadControl getDefaultLoadControl() {
        return new DefaultLoadControl();
    }

    public DataSource.Factory getDefaultDataSourceFactory() {
        DefaultBandwidthMeter bandwidthMeter = (DefaultBandwidthMeter) getDefaultBandwidthMeter();
        return new DefaultDataSourceFactory(context, bandwidthMeter, buildHttpDataSourceFactory(bandwidthMeter));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(userAgent, bandwidthMeter);
    }

}