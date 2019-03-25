package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ActivityMainBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieActivity;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters.MainPagerAdapter;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by TranHuuPhuc on 9/19/18.
 */
public class MainActivity extends BaseMovieActivity<BasePresenter, ActivityMainBinding> implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        viewDataBinding.vpMain.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewDataBinding.bnvMain.setOnNavigationItemSelectedListener(this);
        viewDataBinding.vpMain.addOnPageChangeListener(this);
    }

    @Override
    protected void startScreen() {

    }

    @Override
    protected void resumeScreen() {

    }

    @Override
    protected void pauseScreen() {

    }

    @Override
    protected void destroyScreen() {

    }

    @NonNull
    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_movie:
                viewDataBinding.vpMain.setCurrentItem(0);
                break;
            case R.id.navigation_tv_shows:
                viewDataBinding.vpMain.setCurrentItem(1);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        viewDataBinding.bnvMain.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
