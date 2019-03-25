package com.stdio.hue.base.core;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by TranHuuPhuc on 3/26/18.
 */

public abstract class BaseDataBindingFragment<T extends ViewDataBinding> extends BaseFragment {
    protected T viewDataBinding;

    private boolean isAttach;

    public boolean isAttach() {
        return isAttach;
    }


    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void init(@Nullable View view);

    protected abstract void screenResume();

    protected abstract void screenPause();

    protected abstract void screenStart(@Nullable Bundle saveInstanceState);

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

