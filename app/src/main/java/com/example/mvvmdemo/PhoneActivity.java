package com.example.mvvmdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.mvvmdemo.dagger.component.ActivityComponent;
import com.example.mvvmdemo.dagger.component.DaggerActivityComponent;
import com.example.mvvmdemo.dagger.modul.UtilModule;
import com.example.mvvmdemo.screen.menu.MenuFragment;
import com.example.mvvmdemo.util.FragmentInserter;

public class PhoneActivity extends AppCompatActivity implements StartActivity {

    private FragmentInserter fragmentInserter;
    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        setContentView(R.layout.activity_main);
        fragmentInserter = new FragmentInserter(getSupportFragmentManager());
        loadStartFragment();
    }

    private void loadStartFragment() {
        fragmentInserter.replaceFragment(
                R.id.main_fragment_container,
                Fragment.instantiate(this, MenuFragment.class.getName()),
                null,
                null);
    }

    private void initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(((BaseApplication) getApplication()).getAppComponent())
                .utilModule(new UtilModule(false))
                .build();
    }

    @Override
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
