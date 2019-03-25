package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.di;

import com.stdio.hue.data.usecases.MovieUseCase;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenterImpl;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.MovieAction;

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
    MoviePresenter providesMoviePresenter(PublishSubject<MovieAction> movieActionPublishSubject, @Named("api_key") String apiKey, MovieUseCase movieUseCase) {
        return new MoviePresenterImpl(movieActionPublishSubject, apiKey, movieUseCase);
    }

}
