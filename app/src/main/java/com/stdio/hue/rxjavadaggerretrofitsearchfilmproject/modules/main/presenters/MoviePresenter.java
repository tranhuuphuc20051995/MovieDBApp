package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters;

import com.stdio.hue.base.core.mvp.Presenter;
import com.stdio.hue.data.models.Movie;
import com.stdio.hue.data.models.bases.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by TranHuuPhuc on 9/25/18.
 */
public interface MoviePresenter extends Presenter {
    void getMainMovieData();

    void getMovieDetail(int movieId);

    void getUpcomingMoreMovie(int page);

    void getTopRateMoreMovie(int page);

    void getPopularMoreMovie(int page);

    void getNowPlayingMoreMovie(int page);

    void getRecommendations(int movieId, int page);

    void getSimilarMovies(int movieId, int page);

    void getVideoTrailer(int movieId);

    void searchMovies( String query, int page);

    Observable<BaseResponse<List<Movie>>> getUpcoming(String apiKey, int page);

    Observable<BaseResponse<List<Movie>>> getTopRated(String apiKey, int page);

    Observable<BaseResponse<List<Movie>>> getPopular(String apiKey, int page);

    Observable<BaseResponse<List<Movie>>> getNowPlaying(String apiKey, int page);

    Observable<BaseResponse<List<Movie>>> getTrendings(String apiKey, int page);
}
