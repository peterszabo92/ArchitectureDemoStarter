package com.example.mvvmdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.mvvmdemo.base.model.ViewModel;

public abstract class BaseViewModelFragment<VM extends ViewModel> extends BaseFragment {

    protected VM viewModel;

    protected abstract VM createViewModel();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewModel = createViewModel();
        super.onViewCreated(view, savedInstanceState);
    }
}
