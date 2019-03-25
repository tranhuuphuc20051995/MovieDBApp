package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stdio.hue.base.core.BasePresenterActivity;
import com.stdio.hue.base.core.mvp.Presenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.ProjectApplication;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.di.components.AppComponent;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.share.LoadingDialog;

/**
 * Created by TranHuuPhuc on 7/6/18.
 */
public abstract class BaseMovieActivity<P extends Presenter, V extends ViewDataBinding> extends BasePresenterActivity<P> {
    protected V viewDataBinding;
    protected LoadingDialog loadingDialog;
    protected Gson gson;

    /**
     * setup content layout
     *
     * @return layout id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * init for data
     */
    protected abstract void init();

    /**
     * start screen
     */
    protected abstract void startScreen();

    /**
     * resume screen
     */
    protected abstract void resumeScreen();

    /**
     * pause screen
     */
    protected abstract void pauseScreen();

    /**
     * destroy screen
     */
    protected abstract void destroyScreen();

    protected AppComponent getAppComponent() {
        return ((ProjectApplication) getApplication()).getAppComponent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        gson = new GsonBuilder().create();
        init();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        startScreen();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startScreen();
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeScreen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyScreen();
    }

    protected void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.show();
    }

    protected void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    protected void loading(boolean isShow) {
        if (isShow) {
            showLoading();
        } else {
            hideLoading();
        }
    }
}
