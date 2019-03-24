package com.stdio.hue.base.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.stdio.hue.base.core.mvp.Presenter;
import com.stdio.hue.base.core.mvp.PresenterDelegate;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by TranHuuPhuc on 7/6/18.
 */
public abstract class BasePresenterActivity<P extends Presenter> extends BaseActivity implements Presenter.View {
    protected abstract @NonNull
    P createPresenter();

    private P presenter;

    protected P getPresenter() {
        return presenter;
    }

    private PresenterDelegate presenterDelegate;

    protected CompositeDisposable disposableManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposableManager = new CompositeDisposable();
        presenterDelegate = new PresenterDelegate(presenter = createPresenter());
        presenterDelegate.attach(this);
        presenterDelegate.init();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenterDelegate.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenterDelegate.pause();
    }

    @Override
    protected void onDestroy() {
        disposableManager.clear();
        presenterDelegate.detach();
        presenterDelegate.destroy();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenterDelegate.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenterDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
