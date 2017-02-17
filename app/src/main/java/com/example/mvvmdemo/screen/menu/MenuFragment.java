package com.example.mvvmdemo.screen.menu;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.StartActivity;
import com.example.mvvmdemo.base.BaseFragment;
import com.example.mvvmdemo.screen.gallery.GalleryFragment;
import com.example.mvvmdemo.screen.gallery.fragment.PictureListFragment;
import com.example.mvvmdemo.screen.playerdemo.PlayerDemoFragment;
import com.example.mvvmdemo.util.DimensionUtil;
import com.example.mvvmdemo.util.FragmentInserter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MenuFragment extends BaseFragment {

    @BindView(R.id.menu_exoplayer) Button menuExoplayer;
    @BindView(R.id.menu_mvvm) Button menuMvvm;

    private FragmentInserter fragmentInserter;

    @Inject DimensionUtil dimensionUtil;

    @Override
    protected void init(View view) {
        ((StartActivity) getActivity()).getActivityComponent().inject(this);
        fragmentInserter = new FragmentInserter(getFragmentManager());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_menu;
    }

    @OnClick(R.id.menu_exoplayer)
    public void onExoPlayerMenuClick() {
        fragmentInserter.replaceFragment(
                R.id.main_fragment_container,
                Fragment.instantiate(getActivity(), PlayerDemoFragment.class.getName()),
                null,
                "");
    }

    @OnClick(R.id.menu_mvvm)
    public void onMvvmMenuClick() {
        fragmentInserter.replaceFragment(
                R.id.main_fragment_container,
                Fragment.instantiate(
                        getActivity(),
                        dimensionUtil.isTablet() ? GalleryFragment.class.getName() : PictureListFragment.class.getName()),
                null,
                "");
    }
}
