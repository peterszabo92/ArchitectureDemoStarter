package com.example.mvvmdemo.gallery.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseViewModelFragment;
import com.example.mvvmdemo.gallery.GalleryPageContract;
import com.example.mvvmdemo.gallery.adapter.PictureListAdapter;
import com.example.mvvmdemo.gallery.adapter.PictureListAdapterDataBinding;
import com.example.mvvmdemo.gallery.adapter.SpacesItemDecoration;
import com.example.mvvmdemo.gallery.model.PictureListItem;
import com.example.mvvmdemo.gallery.viewmodel.PictureListViewModel;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;

public class PictureListFragment extends BaseViewModelFragment<GalleryPageContract.PictureListViewModel> {

    @BindView(R.id.picture_list)
    RecyclerView pictureList;

    private PictureListAdapter adapter;
    private PictureListAdapterDataBinding adapterDataBinding;


    @Override
    protected void init(View view) {
        adapter = new PictureListAdapter();
        adapterDataBinding = new PictureListAdapterDataBinding();

        pictureList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.picture_item_space);
        pictureList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        pictureList.setAdapter(adapterDataBinding);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Subscribe on picture items
        compositeSubscription.add(viewModel.getPictureItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapterDataBinding::setDataList));

        // Subscribe on picture item click
        compositeSubscription.add(adapterDataBinding.getPositionClicks().subscribe(listItem -> {
            viewModel.selectImage(((PictureListItem) listItem).getData());
        }));
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
