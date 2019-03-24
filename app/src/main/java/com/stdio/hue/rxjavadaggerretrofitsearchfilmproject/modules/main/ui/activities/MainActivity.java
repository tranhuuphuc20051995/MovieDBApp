package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ActivityMainBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieActivity;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.fragments.MainFragment;

/**
 * Created by TranHuuPhuc on 9/19/18.
 */
public class MainActivity extends BaseMovieActivity<BasePresenter, ActivityMainBinding> {
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
        replaceFragment(viewDataBinding.frameContent.getId(), MainFragment.newInstance(), false);
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

}
