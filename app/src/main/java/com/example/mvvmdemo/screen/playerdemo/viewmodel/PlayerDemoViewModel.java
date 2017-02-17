package com.example.mvvmdemo.screen.playerdemo.viewmodel;

import android.net.Uri;
import android.os.Handler;

import com.example.mvvmdemo.base.model.ViewModel;
import com.example.mvvmdemo.player.PlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.upstream.DataSpec;

import java.io.IOException;

import javax.inject.Inject;

public class PlayerDemoViewModel implements ViewModel {

    private PlayerFactory playerFactory;

    @Inject
    public PlayerDemoViewModel(PlayerFactory playerFactory) {
        this.playerFactory = playerFactory;
    }

    public SimpleExoPlayer getPlayer() {
        return playerFactory.createSimplePlayer();
    }

    public MediaSource getMediaSource() {
        return new DashMediaSource(
                Uri.parse("http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0"),
                playerFactory.getDefaultDataSourceFactory(),
                new DefaultDashChunkSource.Factory(playerFactory.getDefaultDataSourceFactory()),
                new Handler(),
                new AdaptiveMediaSourceEventListener() {
                    @Override
                    public void onLoadStarted(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs) {

                    }

                    @Override
                    public void onLoadCompleted(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded) {

                    }

                    @Override
                    public void onLoadCanceled(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded) {

                    }

                    @Override
                    public void onLoadError(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded, IOException error, boolean wasCanceled) {

                    }

                    @Override
                    public void onUpstreamDiscarded(int trackType, long mediaStartTimeMs, long mediaEndTimeMs) {

                    }

                    @Override
                    public void onDownstreamFormatChanged(int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaTimeMs) {

                    }
                }
        );

    }

}
