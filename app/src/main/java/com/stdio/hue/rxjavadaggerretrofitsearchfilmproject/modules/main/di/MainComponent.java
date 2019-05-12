package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.di;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.TVShowPresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.MovieAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.TVShowAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions.MoreMovieAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions.MovieDetailAction;

import dagger.Subcomponent;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 7/13/18.
 */
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    PublishSubject<MovieAction> getMovieState();

    PublishSubject<MoreMovieAction> getMoreMovieState();

    PublishSubject<MovieDetailAction> getMovieDetailAction();

    MoviePresenter getMoviePresenter();

    PublishSubject<TVShowAction> getTVShowState();

    TVShowPresenter getTVShowPresenter();
}
