package com.example.mvvmdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

    protected CompositeSubscription compositeSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeSubscription.unsubscribe();
        compositeSubscription.clear();
    }

    protected abstract void init(View view);

    protected abstract int getLayout();

}
