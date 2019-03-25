package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stdio.hue.data.di.DataModule;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.ProjectApplication;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hung.nguyendk on 4/29/18.
 */
@Module(includes = {DataModule.class})
@Singleton
public class AppModule {
    @Provides
    @Named("application")
    Context provideContext(ProjectApplication application) {
        return application.getApplicationContext();
    }

    @Provides
    @Named("api_key")
    public String provideAPIKey() {
        return "28aa4fa810b9a3e6a836ec3eaf3d916e";
    }

    @Provides
    @Singleton
    @Named("default")
    public Gson providesGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    @Named("setting")
    public SharedPreferences providesSettingSharedPreferences(Context context) {
        return context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }
}
