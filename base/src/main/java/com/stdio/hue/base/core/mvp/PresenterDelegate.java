package com.stdio.hue.base.core.mvp;

import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by hung.nguyendk on 4/29/18.
 */
public class PresenterDelegate implements Presenter {

    private Presenter presenter;

    public PresenterDelegate(@NonNull Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void attach(View view) {
        presenter.attach(view);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void detach() {
        presenter.detach();
    }

    @Override
    public void resume() {
        presenter.resume();
    }

    @Override
    public void pause() {
        presenter.pause();
    }

    @Override
    public void init() {
        presenter.init();
    }

    @Override
    public void destroy() {
        presenter.destroy();
    }
}
