package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters;

import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.MainAction;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 9/25/18.
 */
public class MainPresenterImpl extends BasePresenter implements MainPresenter {
    private PublishSubject<MainAction> mainActionPublishSubject;

    public MainPresenterImpl(PublishSubject<MainAction> mainActionPublishSubject) {
        this.mainActionPublishSubject = mainActionPublishSubject;
    }

}
