package com.example.mvvmdemo.base.view;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmdemo.R;

import butterknife.ButterKnife;

public class StateLayout extends FrameLayout {

    private int loadingViewResId;
    private int errorViewResId;
    private int errorTextId;
    private int emptyViewResId;
    private int emptyTextId;

    private View contentView;
    private View loadingView;
    private View errorView;
    private View emptyView;

    public StateLayout(Context context) {
        this(context, null);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.StateLayout, 0, 0);
        try {
            loadingViewResId = a.getResourceId(R.styleable.StateLayout_loading_view_layout, R.layout.layout_state_layout_default_loading_view);

            errorViewResId = a.getResourceId(R.styleable.StateLayout_error_view_layout, R.layout.layout_state_layout_default_error_view);
            errorTextId = a.getResourceId(R.styleable.StateLayout_error_text, R.string.all_loading_error);

            emptyViewResId = a.getResourceId(R.styleable.StateLayout_empty_view_layout, R.layout.layout_state_layout_default_empty_view);
            emptyTextId = a.getResourceId(R.styleable.StateLayout_empty_text, R.string.all_no_content);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 1) {
            throw new IllegalStateException("StateLayout can host only one direct child");
        }
        contentView = getChildAt(0);
    }

    /////////////

    public void showContent() {
        hideLoading();
        hideError();
        hideEmpty();
        contentView.setAlpha(0f);
        contentView.setVisibility(VISIBLE);
        contentView.animate().alpha(1f).setDuration(1000).start();
    }

    public void hideContent() {
        contentView.setVisibility(VISIBLE);
    }

    /////////////

    public void showLoading() {
        if (loadingView == null) {
            loadingView = LayoutInflater.from(getContext()).inflate(loadingViewResId, null);
        }
        hideContent();
        hideError();
        hideEmpty();
        addView(loadingView);
    }

    public void hideLoading() {
        if (loadingView != null) {
            loadingView.animate().alpha(0f).setDuration(500).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    removeView(loadingView);
                    loadingView = null;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            }).start();
        }
    }

    /////////////

    public void showError() {
        if (errorView == null) {
            errorView = LayoutInflater.from(getContext()).inflate(errorViewResId, null);
        }
        TextView errorText = ButterKnife.findById(errorView, R.id.layout_state_layout_default_error_view_text);
        if (errorText != null) {
            errorText.setText(errorTextId);
        }
        hideContent();
        addView(errorView);
    }

    public void hideError() {
        if (errorView != null) {
            removeView(errorView);
            errorView = null;
        }
    }

    /////////////

    public void showEmpty() {
        if (emptyView == null) {
            emptyView = LayoutInflater.from(getContext()).inflate(emptyViewResId, null);
        }
        TextView emptyText = ButterKnife.findById(emptyView, R.id.layout_state_layout_default_empty_view_text);
        if (emptyText != null) {
            emptyText.setText(emptyTextId);
        }
        hideContent();
        addView(emptyView);
    }

    public void hideEmpty() {
        if (emptyView != null) {
            removeView(emptyView);
            emptyView = null;
        }
    }

}
