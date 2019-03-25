package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.FragmentTvShowBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class TVShowFragment extends BaseMovieFragment<BasePresenter, FragmentTvShowBinding> {
    public static TVShowFragment newInstance() {
        Bundle args = new Bundle();
        TVShowFragment fragment = new TVShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tv_show;
    }

    @Override
    protected void init(@Nullable View view) {

    }

    @Override
    protected void screenResume() {

    }

    @Override
    protected void screenPause() {

    }

    @Override
    protected void screenStart(@Nullable Bundle saveInstanceState) {

    }

    @Override
    protected void attach(Context context) {

    }

    @NonNull
    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }
}
