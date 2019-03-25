package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions;

import com.stdio.hue.data.models.Movie;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 7/13/18.
 */
public class MovieAction {
    public static final PublishSubject<MovieAction> publisher = PublishSubject.create();
    private boolean isLoading;
    private String errorMessage;
    private List<List<Movie>> listMainMovies;

    public static MovieAction isLoading(boolean isLoading) {
        MovieAction action = new MovieAction();
        action.isLoading = isLoading;
        return action;
    }

    public static MovieAction error(String mess) {
        MovieAction action = new MovieAction();
        action.errorMessage = mess;
        return action;
    }

    public static MovieAction setListMovies(List<List<Movie>> listMainMovies) {
        MovieAction action = new MovieAction();
        action.listMainMovies = listMainMovies;
        return action;
    }

    public List<List<Movie>> getListMovies() {
        return listMainMovies;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public String getError() {
        return errorMessage;
    }
}
