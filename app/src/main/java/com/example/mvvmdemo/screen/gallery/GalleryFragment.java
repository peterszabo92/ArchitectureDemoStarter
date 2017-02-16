package com.example.mvvmdemo.screen.gallery;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseFragment;
import com.example.mvvmdemo.screen.gallery.fragment.BigPictureFragment;
import com.example.mvvmdemo.screen.gallery.fragment.PictureDescriptionFragment;
import com.example.mvvmdemo.screen.gallery.fragment.PictureListFragment;
import com.example.mvvmdemo.util.AppConstants;
import com.example.mvvmdemo.util.FragmentInserter;

import butterknife.BindView;

public class GalleryFragment extends BaseFragment {

    @BindView(R.id.main_container)
    FrameLayout mainContainer;

    private FragmentInserter fragmentInserter;

    @Override
    protected void init(View view) {
        fragmentInserter = new FragmentInserter(getChildFragmentManager());
        mainContainer.post(() -> {
            int width = mainContainer.getWidth();
            int height = (int) (width * (AppConstants.RATIO_916));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mainContainer.getLayoutParams();
            params.height = height;
            mainContainer.setLayoutParams(params);

            loadSubPages();
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gallery;
    }

    private void loadSubPages() {
        Context context = getActivity().getApplicationContext();
        fragmentInserter.replaceFragment(
                R.id.main_container,
                Fragment.instantiate(context, BigPictureFragment.class.getName()),
                null,
                null);

        fragmentInserter.replaceFragment(
                R.id.desc_container,
                Fragment.instantiate(context, PictureDescriptionFragment.class.getName()),
                null,
                null);

        fragmentInserter.replaceFragment(
                R.id.side_container,
                Fragment.instantiate(context, PictureListFragment.class.getName()),
                null,
                null);
    }
}