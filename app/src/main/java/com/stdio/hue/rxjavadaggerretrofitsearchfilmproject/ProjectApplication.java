package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject;

import android.support.multidex.MultiDexApplication;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.di.components.AppComponent;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.di.components.DaggerAppComponent;

/**
 * Created by TranHuuPhuc on 7/6/18.
 */
public class ProjectApplication extends MultiDexApplication {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent().inject(this);
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .context(this)
                    .build();
        }
        return appComponent;
    }
}
