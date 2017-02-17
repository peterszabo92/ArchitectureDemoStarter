package com.example.mvvmdemo.screen.playerdemo;

import android.view.View;
import android.widget.FrameLayout;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.StartActivity;
import com.example.mvvmdemo.base.BaseViewModelFragment;
import com.example.mvvmdemo.player.view.DefaultExoPlayerView;
import com.example.mvvmdemo.screen.playerdemo.viewmodel.PlayerDemoViewModel;
import com.example.mvvmdemo.util.AppConstants;
import com.google.android.exoplayer2.SimpleExoPlayer;

import butterknife.BindView;

public class PlayerDemoFragment extends BaseViewModelFragment<PlayerDemoViewModel> {

    @BindView(R.id.player_view) DefaultExoPlayerView playerView;

    private SimpleExoPlayer player;

    @Override
    protected PlayerDemoViewModel createViewModel() {
        return ((StartActivity) getActivity()).getActivityComponent().getPlayerDemoViewModel();
    }

    @Override
    protected void init(View view) {
        playerView.post(() -> {
            int width = playerView.getWidth();
            int height = (int) (width * AppConstants.RATIO_916);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) playerView.getLayoutParams();
            params.height = height;
            playerView.setLayoutParams(params);


            BaseApplication.imageLoader.loadSimpleImageFromUrl(
                    playerView.getPreview().getPreviewImage(),
                    "http://i.imgur.com/s6mFAdX.jpg",
                    width,
                    height);
        });

        player = viewModel.getPlayer();
        playerView.setPlayer(player);
        playerView.getPreview().setOnPlayClickListener(v -> {
            player.setPlayWhenReady(true);
            player.prepare(viewModel.getMediaSource());
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player.release();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_player_demo;
    }

}
