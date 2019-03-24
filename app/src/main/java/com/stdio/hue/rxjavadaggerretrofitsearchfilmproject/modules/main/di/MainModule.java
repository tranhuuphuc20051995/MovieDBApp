package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.di;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MainPresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MainPresenterImpl;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.MainAction;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 7/13/18.
 */
@Module
public class MainModule {
    @Provides
    PublishSubject<MainAction> providesMainActionPublishSubject() {
        return MainAction.publisher;
    }

    @Provides
    MainPresenter providesMainPresenter(PublishSubject<MainAction> mainActionPublishSubject) {
        return new MainPresenterImpl(mainActionPublishSubject);
    }

}
