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

    Observable<BaseResponse<List<Movie>>> getUpcoming(String apiKey, int page);

    Observable<BaseResponse<List<Movie>>> getTopRated(String apiKey, int page);

    Observable<BaseResponse<List<Movie>>> getPopular(String apiKey, int page);

    Observable<BaseResponse<List<Movie>>> getNowPlaying(String apiKey, int page);

    Observable<BaseResponse<List<Movie>>> getTrendings(String apiKey, int page);
}
