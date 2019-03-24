package com.stdio.hue.data.usecases.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stdio.hue.data.models.Movie;
import com.stdio.hue.data.models.Video;
import com.stdio.hue.data.models.bases.BaseResponse;
import com.stdio.hue.data.usecases.MovieUseCase;
import com.stdio.hue.network.service.MovieService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TranHuuPhuc on 3/24/19.
 */
public class MovieUseCaseImpl extends BaseUseCase implements MovieUseCase {
    private MovieService movieService;

    public MovieUseCaseImpl(Gson gson, MovieService movieService) {
        super(gson);
        this.movieService = movieService;
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getTrendingListForTheDay(String apiKey, String page) {
        return movieService.getTrendingListForTheDay(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getNowPlaying(String apiKey, String page) {
        return movieService.getNowPlaying(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getPopulars(String apiKey, String page) {
        return movieService.getPopulars(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getTopRated(String apiKey, String page) {
        return movieService.getTopRated(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getUpcoming(String apiKey, String page) {
        return movieService.getUpcoming(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<Movie>> getMovieDetail(String apiKey, String movieId) {
        return movieService.getMovieDetail(apiKey, movieId)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<Movie>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getRecommendations(String apiKey, String movieId, String page) {
        return movieService.getRecommendations(apiKey, movieId, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getSimilarMovies(String apiKey, String movieId, String page) {
        return movieService.getSimilarMovies(apiKey, movieId, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Video>>> getVideoTrailer(String apiKey, String movieId) {
        return movieService.getVideoTrailer(apiKey, movieId)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Video>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> searchMovies(String apiKey, String query, String page) {
        return movieService.searchMovies(apiKey, query, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Movie>>>() {
                }.getType()));
    }
}
