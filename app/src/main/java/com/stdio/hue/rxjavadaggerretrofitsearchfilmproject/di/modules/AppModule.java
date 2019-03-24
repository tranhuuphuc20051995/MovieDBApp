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
