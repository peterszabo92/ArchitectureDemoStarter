package com.example.mvvmdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmdemo.base.model.ViewModel;

import butterknife.ButterKnife;

public abstract class BaseFragment<VM extends ViewModel> extends Fragment {

    protected VM viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        viewModel = createViewModel();
        init(view);
    }

    protected abstract void init(View view);

    protected abstract int getLayout();

    protected abstract VM createViewModel();

}
