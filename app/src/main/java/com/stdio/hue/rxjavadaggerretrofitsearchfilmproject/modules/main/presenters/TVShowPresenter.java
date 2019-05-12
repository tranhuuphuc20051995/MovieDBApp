package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters;

import com.stdio.hue.base.core.mvp.Presenter;
import com.stdio.hue.data.models.TVShow;
import com.stdio.hue.data.models.bases.BaseResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by TranHuuPhuc on 2019-05-12.
 */
public interface TVShowPresenter extends Presenter {
    void getTVShows();

//    void getTrendingMoreTVShow(int page);

//    void getAiringTodayMoreTVShow(int page);

//    void getTopRateMoreTVShow(int page);

//    void getPopularMoreTVShow(int page);

//    void getRecommendations(int tvId, int page);

//    void getSimilarTVShow(int tvId, int page);

//    void getVideoTrailer(int tvId);

//    void searchTVShow(String query, int page);

//    void getTVShowDetail(int tvId);

    Observable<BaseResponse<List<TVShow>>> getAiringToday(String apiKey, int page);

    Observable<BaseResponse<List<TVShow>>> getTopRated(String apiKey, int page);

    Observable<BaseResponse<List<TVShow>>> getPopular(String apiKey, int page);

    Observable<BaseResponse<List<TVShow>>> getTrendings(String apiKey, int page);
}
