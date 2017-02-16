package com.example.mvvmdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.view.StateLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

    @Nullable @BindView(R.id.state_layout) protected StateLayout stateLayout;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

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
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onPause() {
        super.onPause();
        compositeDisposable.dispose();
        compositeDisposable.clear();
    }

    protected abstract void init(View view);

    protected abstract int getLayout();

    @Nullable
    public StateLayout getStateLayout() {
        if (getParentFragment() != null && ((BaseFragment)getParentFragment()).getStateLayout() != null) {
            return ((BaseFragment)getParentFragment()).getStateLayout();
        }
        return stateLayout;
    }
}
