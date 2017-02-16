package com.example.mvvmdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.mvvmdemo.data.datacontroller.ImageDataController;
import com.example.mvvmdemo.gallery.GalleryFragment;
import com.example.mvvmdemo.util.FragmentInserter;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private FragmentInserter fragmentInserter;

    @Inject
    ImageDataController imageDataController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentInserter = new FragmentInserter(getSupportFragmentManager());
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        loadGalleryFragment();
    }

    private void loadGalleryFragment() {
        fragmentInserter.replaceFragment(
                R.id.main_fragment_container,
                Fragment.instantiate(this, GalleryFragment.class.getName()),
                null);
    }
}
