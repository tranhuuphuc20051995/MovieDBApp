package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.di;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.MovieAction;

import dagger.Subcomponent;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 7/13/18.
 */
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    PublishSubject<MovieAction> getMovieState();

    MoviePresenter getMoviePresenter();
}
