package com.example.mvvmdemo.util;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentInserter {

    private FragmentManager supportFragmentManager;

    public FragmentInserter(FragmentManager supportFragmentManager) {
        this.supportFragmentManager = supportFragmentManager;
    }

    public void replaceFragment(
            @IdRes int containerId,
            Fragment supportFragment,
            @Nullable String fragmentTag,
            @Nullable String backStackTag) {

        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction()
                .replace(containerId, supportFragment, fragmentTag);
        if (backStackTag != null) {
            fragmentTransaction.addToBackStack(backStackTag);
        }
        fragmentTransaction.commit();
    }

}
