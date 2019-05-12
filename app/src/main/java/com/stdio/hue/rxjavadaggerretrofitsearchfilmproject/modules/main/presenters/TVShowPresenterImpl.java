package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters;

import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.data.models.TVShow;
import com.stdio.hue.data.models.bases.BaseResponse;
import com.stdio.hue.data.usecases.TVShowUseCase;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.TVShowAction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 2019-05-12.
 */
public class TVShowPresenterImpl extends BasePresenter implements TVShowPresenter {
    private PublishSubject<TVShowAction> tvShowActionPublishSubject;
    private String apiKey;
    private TVShowUseCase tvShowUseCase;

    public TVShowPresenterImpl(PublishSubject<TVShowAction> tvShowActionPublishSubject, String apiKey, TVShowUseCase tvShowUseCase) {
        this.tvShowActionPublishSubject = tvShowActionPublishSubject;
        this.apiKey = apiKey;
        this.tvShowUseCase = tvShowUseCase;
    }

    @Override
    public void getTVShows() {
        disposable.add(Observable.combineLatest(
                getTrendings(apiKey, 1),
                getAiringToday(apiKey, 1),
                getPopular(apiKey, 1),
                getTopRated(apiKey, 1),
                (baseResponseTrending, baseResponseAiringToday, baseResponsePopular, baseResponseTopRated) -> {
                    List<List<TVShow>> list = new ArrayList<>();
                    list.add(baseResponseTrending.getResults());
                    list.add(baseResponseAiringToday.getResults());
                    list.add(baseResponsePopular.getResults());
                    list.add(baseResponseTopRated.getResults());
                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(true)))
                .doOnError(throwable -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
                .doOnComplete(() -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
                .subscribe(
                        listObject -> tvShowActionPublishSubject.onNext(TVShowAction.setListTVShows(listObject)),
                        throwable -> tvShowActionPublishSubject.onNext(TVShowAction.error(throwable.getMessage()))
                ));
    }

//    @Override
//    public void getTrendingMoreTVShow(int page) {
//        disposable.add(getTrendings(apiKey, page)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(true)))
//                .doOnError(throwable -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
//                .doOnComplete(() -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
//                .subscribe(baseResponse -> tvShowActionPublishSubject.onNext(TVShowAction.setListTVShows(baseResponse.getResults())),
//                        throwable -> tvShowActionPublishSubject.onNext(TVShowAction.error(throwable.getMessage()))));
//    }
//
//    @Override
//    public void getAiringTodayMoreTVShow(int page) {
//        disposable.add(getAiringToday(apiKey, page)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(true)))
//                .doOnError(throwable -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
//                .doOnComplete(() -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
//                .subscribe(baseResponse -> tvShowActionPublishSubject.onNext(TVShowAction.setListTVShows(baseResponse.getResults())),
//                        throwable -> tvShowActionPublishSubject.onNext(TVShowAction.error(throwable.getMessage()))));
//    }
//
//    @Override
//    public void getTopRateMoreTVShow(int page) {
//        disposable.add(getTopRated(apiKey, page)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(true)))
//                .doOnError(throwable -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
//                .doOnComplete(() -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
//                .subscribe(baseResponse -> tvShowActionPublishSubject.onNext(TVShowAction.setListTVShows(baseResponse.getResults())),
//                        throwable -> tvShowActionPublishSubject.onNext(TVShowAction.error(throwable.getMessage()))));
//    }
//
//    @Override
//    public void getPopularMoreTVShow(int page) {
//        disposable.add(getPopular(apiKey, page)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(true)))
//                .doOnError(throwable -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
//                .doOnComplete(() -> tvShowActionPublishSubject.onNext(TVShowAction.isLoading(false)))
//                .subscribe(baseResponse -> tvShowActionPublishSubject.onNext(TVShowAction.setListMovies(baseResponse.getResults())),
//                        throwable -> tvShowActionPublishSubject.onNext(TVShowAction.error(throwable.getMessage()))));
//    }
//
//    @Override
//    public void getRecommendations(int tvId, int page) {
//        disposable.add(tvShowUseCase.getRecommendations(apiKey, movieId, page)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
//                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .subscribe(baseResponse -> {
//                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setListRecommendations(baseResponse.getResults()));
//                        },
//                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
//    }
//
//    @Override
//    public void getSimilarTVShow(int tvId, int page) {
//        disposable.add(tvShowUseCase.getSimilarTVShows(apiKey, movieId, page)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
//                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .subscribe(baseResponse -> {
//                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setListSimilarMovies(baseResponse.getResults()));
//                        },
//                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
//    }
//
//    @Override
//    public void getVideoTrailer(int tvId) {
//        disposable.add(tvShowUseCase.getVideo(apiKey, movieId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
//                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .subscribe(baseResponse -> {
//                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setListVideo(baseResponse.getResults()));
//                        },
//                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
//    }
//
//    @Override
//    public void searchTVShow(String query, int page) {
//        disposable.add(tvShowUseCase.searchTVShows(apiKey, query, page)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
//                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .subscribe(baseResponse -> {
//                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setListSearchMovies(baseResponse.getResults()));
//                        },
//                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
//    }
//
//    @Override
//    public void getTVShowDetail(int tvId) {
//        disposable.add(tvShowUseCase.getDetails(apiKey, movieId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
//                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
//                .subscribe(baseResponse -> {
//                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setMovie(baseResponse.getResults()));
//                        },
//                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
//    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getAiringToday(String apiKey, int page) {
        return tvShowUseCase.getTvAiringToday(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getTopRated(String apiKey, int page) {
        return tvShowUseCase.getTopRated(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getPopular(String apiKey, int page) {
        return tvShowUseCase.getPopulars(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    @Override
    public Observable<BaseResponse<List<TVShow>>> getTrendings(String apiKey, int page) {
        return tvShowUseCase.getTrendingListForTheDay(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }
}
