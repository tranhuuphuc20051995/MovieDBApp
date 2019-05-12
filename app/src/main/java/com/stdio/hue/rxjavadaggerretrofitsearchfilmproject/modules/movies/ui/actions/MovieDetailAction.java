package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions;

import com.stdio.hue.data.models.Movie;
import com.stdio.hue.data.models.Video;

import java.util.List;

import androidx.lifecycle.ViewModel;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 7/13/18.
 */
public class MovieDetailAction {
    public static final PublishSubject<MovieDetailAction> publisher = PublishSubject.create();
    private boolean isLoading;
    private String errorMessage;
    private Movie movie;
    private List<Video> videos;
    private List<Movie> listSimilarMovies, listRecommendations, listSearchMovies;

    public static MovieDetailAction isLoading(boolean isLoading) {
        MovieDetailAction action = new MovieDetailAction();
        action.isLoading = isLoading;
        return action;
    }

    public static MovieDetailAction error(String mess) {
        MovieDetailAction action = new MovieDetailAction();
        action.errorMessage = mess;
        return action;
    }

    public static MovieDetailAction setMovie(Movie movie) {
        MovieDetailAction action = new MovieDetailAction();
        action.movie = movie;
        return action;
    }

    public static MovieDetailAction setListVideo(List<Video> videos) {
        MovieDetailAction action = new MovieDetailAction();
        action.videos = videos;
        return action;
    }

    public static MovieDetailAction setListSimilarMovies(List<Movie> movies) {
        MovieDetailAction action = new MovieDetailAction();
        action.listSimilarMovies = movies;
        return action;
    }

    public static MovieDetailAction setListRecommendations(List<Movie> movies) {
        MovieDetailAction action = new MovieDetailAction();
        action.listRecommendations = movies;
        return action;
    }

    public static MovieDetailAction setListSearchMovies(List<Movie> movies) {
        MovieDetailAction action = new MovieDetailAction();
        action.listSearchMovies = movies;
        return action;
    }

    public List<Movie> getListSimilarMovies() {
        return listSimilarMovies;
    }

    public List<Movie> getListRecommendations() {
        return listRecommendations;
    }

    public List<Movie> getListSearchMovies() {
        return listSearchMovies;
    }

    public Movie getMovie() {
        return movie;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public String getError() {
        return errorMessage;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
