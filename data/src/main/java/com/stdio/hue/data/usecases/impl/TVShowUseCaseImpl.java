package com.stdio.hue.data.usecases.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stdio.hue.data.models.TVShow;
import com.stdio.hue.data.models.Video;
import com.stdio.hue.data.models.bases.BaseResponse;
import com.stdio.hue.data.usecases.TVShowUseCase;
import com.stdio.hue.network.service.TVService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TranHuuPhuc on 2019-05-12.
 */
public class TVShowUseCaseImpl extends BaseUseCase implements TVShowUseCase {
    private TVService tvService;

    public TVShowUseCaseImpl(Gson gson, TVService tvService) {
        super(gson);
        this.tvService = tvService;
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getTrendingListForTheDay(String apiKey, int page) {
        return tvService.getTrendingListForTheDay(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<TVShow>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getTvAiringToday(String apiKey, int page) {
        return tvService.getTvAiringToday(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<TVShow>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getPopulars(String apiKey, int page) {
        return tvService.getPopulars(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<TVShow>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getTopRated(String apiKey, int page) {
        return tvService.getTopRated(apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<TVShow>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<TVShow>> getDetails(int movieId, String apiKey) {
        return tvService.getDetails(movieId, apiKey)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<TVShow>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getRecommendations(int movieId, String apiKey, int page) {
        return tvService.getRecommendations(movieId, apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<TVShow>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getSimilarTVShows(int movieId, String apiKey, int page) {
        return tvService.getSimilarTVShows(movieId, apiKey, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<TVShow>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<Video>>> getVideo(int movieId, String apiKey) {
        return tvService.getVideo(movieId, apiKey)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<Video>>>() {
                }.getType()));
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> searchTVShows(String apiKey, String query, int page) {
        return tvService.searchTVShows(apiKey, query, page)
                .subscribeOn(Schedulers.io())
                .map(s -> getGson().fromJson(s, new TypeToken<BaseResponse<List<TVShow>>>() {
                }.getType()));
    }
}
