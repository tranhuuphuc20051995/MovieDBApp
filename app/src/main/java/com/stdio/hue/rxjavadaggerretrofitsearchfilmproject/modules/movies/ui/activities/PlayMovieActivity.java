package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.activities;

import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieActivity;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

/**
 * Created by TranHuuPhuc on 3/27/19.
 */
public class PlayMovieActivity extends BaseMovieActivity<BasePresenter, ViewDataBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_play_movie;
    }

    @Override
    protected void init() {

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