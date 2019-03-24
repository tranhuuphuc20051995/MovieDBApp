package com.stdio.hue.data.usecases;

import com.stdio.hue.data.models.Movie;
import com.stdio.hue.data.models.Video;
import com.stdio.hue.data.models.bases.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by TranHuuPhuc on 3/24/19.
 */
public interface MovieUseCase {

    Observable<BaseResponse<List<Movie>>> getTrendingListForTheDay(String apiKey, String page);

    Observable<BaseResponse<List<Movie>>> getNowPlaying(String apiKey, String page);

    Observable<BaseResponse<List<Movie>>> getPopulars(String apiKey, String page);

    Observable<BaseResponse<List<Movie>>> getTopRated(String apiKey, String page);

    Observable<BaseResponse<List<Movie>>> getUpcoming(String apiKey, String page);

    Observable<BaseResponse<Movie>> getMovieDetail(String apiKey, String movieId);

    Observable<BaseResponse<List<Movie>>> getRecommendations(String apiKey, String movieId, String page);

    Observable<BaseResponse<List<Movie>>> getSimilarMovies(String apiKey, String movieId, String page);

    Observable<BaseResponse<List<Video>>> getVideoTrailer(String apiKey, String movieId);

    Observable<BaseResponse<List<Movie>>> searchMovies(String apiKey, String query, String page);
}
