package com.stdio.hue.data.usecases;

import com.stdio.hue.data.models.TVShow;
import com.stdio.hue.data.models.Video;
import com.stdio.hue.data.models.bases.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by TranHuuPhuc on 2019-05-12.
 */
public interface TVShowUseCase {

    Observable<BaseResponse<List<TVShow>>> getTrendingListForTheDay(String apiKey, int page);

    Observable<BaseResponse<List<TVShow>>> getTvAiringToday(String apiKey, int page);

    Observable<BaseResponse<List<TVShow>>> getPopulars(String apiKey, int page);

    Observable<BaseResponse<List<TVShow>>> getTopRated(String apiKey, int page);

    Observable<BaseResponse<TVShow>> getDetails(int movieId, String apiKey);

    Observable<BaseResponse<List<TVShow>>> getRecommendations(int movieId, String apiKey, int page);

    Observable<BaseResponse<List<TVShow>>> getSimilarTVShows(int movieId, String apiKey, int page);

    Observable<BaseResponse<List<Video>>> getVideo(int movieId, String apiKey);

    Observable<BaseResponse<List<TVShow>>> searchTVShows(String apiKey, String query, int page);

}
