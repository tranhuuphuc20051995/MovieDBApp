package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.activities;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieActivity;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions.MovieDetailAction;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 3/27/19.
 */
public class MovieDetailActivity extends BaseMovieActivity<MoviePresenter, ViewDataBinding> {
    private static final String EXTRA_MOVIE_ID = "extra-movie-id";

    public static void start(Context context, int movieId) {
        Intent starter = new Intent(context, MovieDetailActivity.class);
        starter.putExtra(EXTRA_MOVIE_ID, movieId);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    private int movieId;
    private PublishSubject<MovieDetailAction> movieDetailActionPublishSubject;

    @Override
    protected void init() {
        if (getIntent() != null) {
            movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, 0);
        }
        movieDetailActionPublishSubject = getAppComponent().getMainComponent().getMovieDetailAction();
        getData();
        displayData();
    }

    private void displayData() {
        disposableManager.add(
                movieDetailActionPublishSubject.map(MovieDetailAction::isLoading)
                        .subscribe(this::loading)
        );

        disposableManager.add(
                movieDetailActionPublishSubject.filter(s -> s.getError() != null && !s.getError().isEmpty())
                        .map(MovieDetailAction::getError)
                        .subscribe(this::showToast)
        );

        disposableManager.add(
                movieDetailActionPublishSubject.filter(c -> c.getMovie() != null)
                        .map(MovieDetailAction::getMovie)
                        .subscribe(listMovies -> {

                        })
        );

        disposableManager.add(
                movieDetailActionPublishSubject.filter(c -> c.getVideos() != null)
                        .map(MovieDetailAction::getVideos)
                        .subscribe(listMovies -> {

                        })
        );

        disposableManager.add(
                movieDetailActionPublishSubject.filter(c -> c.getListRecommendations() != null)
                        .map(MovieDetailAction::getListRecommendations)
                        .subscribe(listMovies -> {

                        })
        );

        disposableManager.add(
                movieDetailActionPublishSubject.filter(c -> c.getListSimilarMovies() != null)
                        .map(MovieDetailAction::getListSimilarMovies)
                        .subscribe(listMovies -> {

                        })
        );
    }

    private void getData() {
        getPresenter().getMovieDetail(movieId);
        getPresenter().getVideoTrailer(movieId);
        getPresenter().getRecommendations(movieId, 1);
        getPresenter().getSimilarMovies(movieId, 1);
    }

    @Override
    protected void startScreen() {

    }

    @Override
    protected void resumeScreen() {

    }

    @Override
    protected void pauseScreen() {

    }

    @Override
    protected void destroyScreen() {

    }

    @NonNull
    @Override
    protected MoviePresenter createPresenter() {
        return getAppComponent().getMainComponent().getMoviePresenter();
    }
}