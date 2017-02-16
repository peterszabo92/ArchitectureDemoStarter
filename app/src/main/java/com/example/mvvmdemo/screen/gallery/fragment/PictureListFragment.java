package com.example.mvvmdemo.screen.gallery.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mvvmdemo.BaseApplication;
import com.example.mvvmdemo.R;
import com.example.mvvmdemo.base.BaseViewModelFragment;
import com.example.mvvmdemo.screen.gallery.GalleryPageContract;
import com.example.mvvmdemo.screen.gallery.adapter.PictureListAdapter;
import com.example.mvvmdemo.screen.gallery.adapter.SpacesItemDecoration;
import com.example.mvvmdemo.screen.gallery.model.PictureListItem;
import com.example.mvvmdemo.screen.gallery.viewmodel.PictureListViewModel;
import com.example.mvvmdemo.util.Logs;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;


public class PictureListFragment extends BaseViewModelFragment<GalleryPageContract.PictureListViewModel> {

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
    }

    @Override
    public void onResume() {
        super.onResume();
        // Subscribe on picture items
        compositeDisposable.add(viewModel.getPictureItems()
                .observeOn(AndroidSchedulers.mainThread())
                //.compose(new DefaultRxComposer().setStateLayout(getStateLayout(), () -> adapter.getItemCount() > 0))
                .subscribe(adapter::setDataList, Logs::s));

        // Subscribe on picture item click
        compositeDisposable.add(adapter.getPositionClicks().subscribe(listItem -> {
            viewModel.selectImage(((PictureListItem) listItem).getData());
        }));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((BaseApplication) getActivity().getApplication()).getAppComponent().getImageManager().getImageModels();
                Toast.makeText(getContext(), "Most", Toast.LENGTH_SHORT).show();
            }
        }, 5000);
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
