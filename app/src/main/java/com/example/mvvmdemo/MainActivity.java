package com.example.mvvmdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;

import com.example.mvvmdemo.gallery.GalleryFragment;
import com.example.mvvmdemo.util.FragmentInserter;

public class MainActivity extends AppCompatActivity {

    private FragmentInserter fragmentInserter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentInserter = new FragmentInserter(getSupportFragmentManager());
        loadGalleryFragment();
    }

    private void loadGalleryFragment() {
        fragmentInserter.replaceFragment(
                R.id.main_fragment_container,
                Fragment.instantiate(this, GalleryFragment.class.getName()),
                null);
    }
}
