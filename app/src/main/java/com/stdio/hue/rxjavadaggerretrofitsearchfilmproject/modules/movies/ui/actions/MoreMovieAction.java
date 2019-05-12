package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions;

import com.stdio.hue.data.models.Movie;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 7/13/18.
 */
public class MoreMovieAction {
    public static final PublishSubject<MoreMovieAction> publisher = PublishSubject.create();
    private boolean isLoading;
    private String errorMessage;
    private List<Movie> listMovie;

    public static MoreMovieAction isLoading(boolean isLoading) {
        MoreMovieAction action = new MoreMovieAction();
        action.isLoading = isLoading;
        return action;
    }

    public static MoreMovieAction error(String mess) {
        MoreMovieAction action = new MoreMovieAction();
        action.errorMessage = mess;
        return action;
    }

    public static MoreMovieAction setListMovies(List<Movie> listMovie) {
        MoreMovieAction action = new MoreMovieAction();
        action.listMovie = listMovie;
        return action;
    }

    public List<Movie> getListMovies() {
        return listMovie;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public String getError() {
        return errorMessage;
    }
}
