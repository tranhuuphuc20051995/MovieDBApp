package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.di;

import com.stdio.hue.data.usecases.MovieUseCase;
import com.stdio.hue.data.usecases.TVShowUseCase;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenterImpl;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.TVShowPresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.TVShowPresenterImpl;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.MovieAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.TVShowAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions.MoreMovieAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions.MovieDetailAction;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 7/13/18.
 */
@Module
public class MainModule {
    @Provides
    PublishSubject<MovieAction> providesMovieActionPublishSubject() {
        return MovieAction.publisher;
    }

    @Provides
    PublishSubject<MoreMovieAction> providesMoreMovieActionPublishSubject() {
        return MoreMovieAction.publisher;
    }

    @Provides
    PublishSubject<MovieDetailAction> providesMovieDetailActionPublishSubject() {
        return MovieDetailAction.publisher;
    }

    @Provides
    MoviePresenter providesMoviePresenter(PublishSubject<MovieAction> movieActionPublishSubject, PublishSubject<MoreMovieAction> moreMovieActionPublishSubject, PublishSubject<MovieDetailAction> movieDetailActionPublishSubject, @Named("api_key") String apiKey, MovieUseCase movieUseCase) {
        return new MoviePresenterImpl(movieActionPublishSubject, moreMovieActionPublishSubject, movieDetailActionPublishSubject, apiKey, movieUseCase);
    }

    @Provides
    TVShowPresenter providesTVShowPresenter(PublishSubject<TVShowAction> tvShowActionPublishSubject, @Named("api_key") String apiKey, TVShowUseCase tvShowUseCase) {
        return new TVShowPresenterImpl(tvShowActionPublishSubject, apiKey, tvShowUseCase);
    }

    @Provides
    PublishSubject<TVShowAction> providesTVShowActionPublishSubject() {
        return TVShowAction.publisher;
    }

}
