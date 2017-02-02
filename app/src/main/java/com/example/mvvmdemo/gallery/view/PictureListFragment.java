package com.example.mvvmdemo.gallery.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseFragment;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.gallery.adapter.PictureListAdapter;
import com.example.mvvmdemo.gallery.adapter.SpacesItemDecoration;
import com.example.mvvmdemo.gallery.model.PictureListItem;
import com.example.mvvmdemo.gallery.viewmodel.PictureListViewModel;

import butterknife.BindView;

public class PictureListFragment extends BaseFragment<GalleryPageContract.PictureListViewModel> {

    @BindView(R.id.picture_list)
    RecyclerView pictureList;

    private PictureListAdapter adapter;

    @Override
    protected void init(View view) {
        adapter = new PictureListAdapter();
        pictureList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.picture_item_space);
        pictureList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        pictureList.setAdapter(adapter);

        viewModel.getPictureItems()
                .subscribe(adapter::setDataList);

        adapter.getPositionClicks().subscribe(listItem -> {
            viewModel.selectImage(((PictureListItem) listItem).getData());
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_picture_list;
    }

    @Override
    protected GalleryPageContract.PictureListViewModel createViewModel() {
        return new PictureListViewModel(
                ((BaseApplication) getActivity().getApplication()).getAppComponent().getImageManager());
    }
}
