package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters;

import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.data.models.Movie;
import com.stdio.hue.data.models.bases.BaseResponse;
import com.stdio.hue.data.usecases.MovieUseCase;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.MovieAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions.MoreMovieAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions.MovieDetailAction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 9/25/18.
 */
public class MoviePresenterImpl extends BasePresenter implements MoviePresenter {
    private PublishSubject<MovieAction> movieActionPublishSubject;
    private PublishSubject<MoreMovieAction> moreMovieActionPublishSubject;
    private PublishSubject<MovieDetailAction> movieDetailActionPublishSubject;
    private String apiKey;
    private MovieUseCase movieUseCase;

    public MoviePresenterImpl(PublishSubject<MovieAction> movieActionPublishSubject, PublishSubject<MoreMovieAction> moreMovieActionPublishSubject, PublishSubject<MovieDetailAction> movieDetailActionPublishSubject, String apiKey, MovieUseCase movieUseCase) {
        this.movieActionPublishSubject = movieActionPublishSubject;
        this.moreMovieActionPublishSubject = moreMovieActionPublishSubject;
        this.movieDetailActionPublishSubject = movieDetailActionPublishSubject;
        this.apiKey = apiKey;
        this.movieUseCase = movieUseCase;
    }

    @Override
    public void getMainMovieData() {
        disposable.add(Observable.combineLatest(
                getTrendings(apiKey, 1),
                getNowPlaying(apiKey, 1),
                getPopular(apiKey, 1),
                getTopRated(apiKey, 1),
                getUpcoming(apiKey, 1),
                (baseResponseTrending, baseResponseNowPlaying, baseResponsePopular, baseResponseTopRated, baseResponseUpcoming) -> {
                    List<List<Movie>> list = new ArrayList<>();
                    list.add(baseResponseTrending.getResults().subList(0, 5));
                    list.add(baseResponseNowPlaying.getResults());
                    list.add(baseResponsePopular.getResults());
                    list.add(baseResponseTopRated.getResults());
                    list.add(baseResponseUpcoming.getResults());
                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> movieActionPublishSubject.onNext(MovieAction.isLoading(true)))
                .doOnError(throwable -> movieActionPublishSubject.onNext(MovieAction.isLoading(false)))
                .doOnComplete(() -> movieActionPublishSubject.onNext(MovieAction.isLoading(false)))
                .subscribe(listObject -> movieActionPublishSubject.onNext(MovieAction.setListMovies(listObject)),
                        throwable -> movieActionPublishSubject.onNext(MovieAction.error(throwable.getMessage()))));
    }

    @Override
    public void getMovieDetail(int movieId) {
        disposable.add(movieUseCase.getMovieDetail(apiKey, movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .subscribe(baseResponse -> {
                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setMovie(baseResponse.getResults()));
                        },
                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));

    }

    @Override
    public void getUpcomingMoreMovie(int page) {
        disposable.add(getUpcoming(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(true)))
                .doOnError(throwable -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(false)))
                .doOnComplete(() -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(false)))
                .subscribe(baseResponse -> moreMovieActionPublishSubject.onNext(MoreMovieAction.setListMovies(baseResponse.getResults())),
                        throwable -> moreMovieActionPublishSubject.onNext(MoreMovieAction.error(throwable.getMessage()))));
    }

    @Override
    public void getTopRateMoreMovie(int page) {
        disposable.add(getUpcoming(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(true)))
                .doOnError(throwable -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(false)))
                .doOnComplete(() -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(false)))
                .subscribe(baseResponse -> moreMovieActionPublishSubject.onNext(MoreMovieAction.setListMovies(baseResponse.getResults())),
                        throwable -> moreMovieActionPublishSubject.onNext(MoreMovieAction.error(throwable.getMessage()))));
    }

    @Override
    public void getPopularMoreMovie(int page) {
        disposable.add(getUpcoming(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(true)))
                .doOnError(throwable -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(false)))
                .doOnComplete(() -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(false)))
                .subscribe(baseResponse -> moreMovieActionPublishSubject.onNext(MoreMovieAction.setListMovies(baseResponse.getResults())),
                        throwable -> moreMovieActionPublishSubject.onNext(MoreMovieAction.error(throwable.getMessage()))));
    }

    @Override
    public void getNowPlayingMoreMovie(int page) {
        disposable.add(getUpcoming(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(true)))
                .doOnError(throwable -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(false)))
                .doOnComplete(() -> moreMovieActionPublishSubject.onNext(MoreMovieAction.isLoading(false)))
                .subscribe(baseResponse -> moreMovieActionPublishSubject.onNext(MoreMovieAction.setListMovies(baseResponse.getResults())),
                        throwable -> moreMovieActionPublishSubject.onNext(MoreMovieAction.error(throwable.getMessage()))));
    }

    @Override
    public void getRecommendations(int movieId, int page) {
        disposable.add(movieUseCase.getRecommendations(apiKey, movieId, page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .subscribe(baseResponse -> {
                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setListRecommendations(baseResponse.getResults()));
                        },
                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
    }

    @Override
    public void getSimilarMovies(int movieId, int page) {
        disposable.add(movieUseCase.getSimilarMovies(apiKey, movieId, page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .subscribe(baseResponse -> {
                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setListSimilarMovies(baseResponse.getResults()));
                        },
                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
    }

    @Override
    public void getVideoTrailer(int movieId) {
        disposable.add(movieUseCase.getVideoTrailer(apiKey, movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .subscribe(baseResponse -> {
                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setListVideo(baseResponse.getResults()));
                        },
                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
    }

    @Override
    public void searchMovies(String query, int page) {
        disposable.add(movieUseCase.searchMovies(apiKey, query, page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(true)))
                .doOnError(throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .doOnComplete(() -> movieDetailActionPublishSubject.onNext(MovieDetailAction.isLoading(false)))
                .subscribe(baseResponse -> {
                            movieDetailActionPublishSubject.onNext(MovieDetailAction.setListSearchMovies(baseResponse.getResults()));
                        },
                        throwable -> movieDetailActionPublishSubject.onNext(MovieDetailAction.error(throwable.getMessage()))));
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getUpcoming(String apiKey, int page) {
        return movieUseCase.getUpcoming(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getTopRated(String apiKey, int page) {
        return movieUseCase.getTopRated(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getPopular(String apiKey, int page) {
        return movieUseCase.getPopulars(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getNowPlaying(String apiKey, int page) {
        return movieUseCase.getNowPlaying(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }

    @Override
    public Observable<BaseResponse<List<Movie>>> getTrendings(String apiKey, int page) {
        return movieUseCase.getTrendingListForTheDay(apiKey, page)
                .observeOn(AndroidSchedulers.mainThread())
                .map(baseResponse -> baseResponse);
    }
}
