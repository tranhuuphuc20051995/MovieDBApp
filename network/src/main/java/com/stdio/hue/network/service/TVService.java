package com.stdio.hue.network.service;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public interface TVService {
    @GET("trending/tv/day")
    Observable<JsonObject> getTrendingListForTheDay(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/airing_today")
    Observable<JsonObject> getTvAiringToday(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/popular")
    Observable<JsonObject> getPopulars(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/top_rated")
    Observable<JsonObject> getTopRated(@Query("api_key") String apiKey, @Query("page") int page);



    @GET("tv/{tv_id}")
    Observable<JsonObject> getDetails(@Path("tv_id") int movieId, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/recommendations")
    Observable<JsonObject> getRecommendations(@Path("tv_id") int movieId, @Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/{tv_id}/similar")
    Observable<JsonObject> getSimilarTVShows(@Path("tv_id") int movieId, @Query("api_key") String apiKey, @Query("page") int page);

    @GET("tv/{tv_id}/videos")
    Observable<JsonObject> getVideo(@Path("tv_id") int movieId, @Query("api_key") String apiKey);

    @GET("search/tv")
    Observable<JsonObject> searchTVShows(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page);

}
