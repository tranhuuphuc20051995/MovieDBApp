package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions;

import com.stdio.hue.data.models.TVShow;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 2019-05-12.
 */
public class TVShowAction {
    public static final PublishSubject<TVShowAction> publisher = PublishSubject.create();
    private boolean isLoading;
    private String errorMessage;
    private List<List<TVShow>> listMainTVShow;

    public static TVShowAction isLoading(boolean isLoading) {
        TVShowAction action = new TVShowAction();
        action.isLoading = isLoading;
        return action;
    }

    public static TVShowAction error(String mess) {
        TVShowAction action = new TVShowAction();
        action.errorMessage = mess;
        return action;
    }

    public static TVShowAction setListTVShows(List<List<TVShow>> listMainMovies) {
        TVShowAction action = new TVShowAction();
        action.listMainTVShow = listMainMovies;
        return action;
    }

    public List<List<TVShow>> getListTVShows() {
        return listMainTVShow;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public String getError() {
        return errorMessage;
    }
}
