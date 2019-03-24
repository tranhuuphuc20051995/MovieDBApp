package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.di.components;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.ProjectApplication;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.di.modules.AppModule;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.di.MainComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(ProjectApplication projectApplication);

        AppComponent build();
    }

    void inject(ProjectApplication projectApplication);

    MainComponent getMainComponent();
}
