package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.splash.ui;

import android.annotation.SuppressLint;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;

import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieActivity;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.activities.MainActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * Created by hung.nguyendk on 4/29/18.
 */
public class SplashActivity extends BaseMovieActivity<BasePresenter, ViewDataBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void startScreen() {
    }

    @SuppressLint("RxSubscribeOnError")
    @Override
    protected void resumeScreen() {
        disposableManager.add(Observable.just(true).delay(3000, TimeUnit.MILLISECONDS).subscribe(v -> {
            MainActivity.start(this);
            finish();
        }));

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
