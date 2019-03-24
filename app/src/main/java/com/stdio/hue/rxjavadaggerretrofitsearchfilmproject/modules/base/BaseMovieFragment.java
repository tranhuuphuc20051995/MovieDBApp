package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stdio.hue.base.core.BasePresenterFragment;
import com.stdio.hue.base.core.mvp.Presenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.ProjectApplication;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.di.components.AppComponent;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.share.LoadingDialog;


/**
 * Created by TranHuuPhuc on 7/6/18.
 */
public abstract class BaseMovieFragment<P extends Presenter, V extends ViewDataBinding> extends BasePresenterFragment<P> {
    protected V viewDataBinding;
    protected Gson gson;
    private boolean isAttach;
    protected LoadingDialog loadingDialog;

    public boolean isAttach() {
        return isAttach;
    }

    protected void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getContext());
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

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void init(@Nullable View view);

    protected abstract void screenResume();

    protected abstract void screenPause();

    protected abstract void screenStart(@Nullable Bundle saveInstanceState);

    protected AppComponent getAppComponent() {
        return ((ProjectApplication) getActivity().getApplication()).getAppComponent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        isAttach = false;
        return viewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        screenStart(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gson = new GsonBuilder().create();
        init(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        screenResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public void onPause() {
        super.onPause();
        screenPause();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isAttach = true;
        attach(context);
    }

    protected abstract void attach(Context context);

}
