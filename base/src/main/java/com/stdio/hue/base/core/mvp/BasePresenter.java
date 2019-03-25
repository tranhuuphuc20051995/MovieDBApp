package com.stdio.hue.base.core.mvp;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter implements Presenter {
    private View view;
    protected CompositeDisposable disposable;

    @Nullable
    public View getView() {
        return view;
    }

    @Override
    public void attach(View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        view = null;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void init() {
        disposable = new CompositeDisposable();
    }

    @Override
    public void destroy() {
        disposable.clear();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
