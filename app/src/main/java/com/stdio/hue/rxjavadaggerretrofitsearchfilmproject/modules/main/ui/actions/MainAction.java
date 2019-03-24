package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 7/13/18.
 */
public class MainAction {
    public static final PublishSubject<MainAction> publisher = PublishSubject.create();
    private boolean isLoading;
    private String errorMessage;
//    private List<Banner> banners;

    public static MainAction isLoading(boolean isLoading) {
        MainAction action = new MainAction();
        action.isLoading = isLoading;
        return action;
    }

    public static MainAction error(String mess) {
        MainAction action = new MainAction();
        action.errorMessage = mess;
        return action;
    }

//    public static MainAction setBanners(List<Banner> data) {
//        MainAction action = new MainAction();
//        action.banners = data;
//        return action;
//    }
//
//    public List<Banner> getBanners() {
//        return banners;
//    }

    public boolean isLoading() {
        return isLoading;
    }

    public String getError() {
        return errorMessage;
    }
}
