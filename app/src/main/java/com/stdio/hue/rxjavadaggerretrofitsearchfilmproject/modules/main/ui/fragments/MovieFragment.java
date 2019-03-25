package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.FragmentMovieBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieFragment;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.MovieAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters.MainMovieAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 3/24/19.
 */
public class MovieFragment extends BaseMovieFragment<MoviePresenter, FragmentMovieBinding> {
    public static MovieFragment newInstance() {
        Bundle args = new Bundle();
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void init(@Nullable View view) {
        viewDataBinding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        PublishSubject<MovieAction> movieActionPublishSubject = getAppComponent().getMainComponent().getMovieState();
        getPresenter().getMainMovieData();
        disposableManager.add(
                movieActionPublishSubject.map(MovieAction::isLoading)
                        .subscribe(this::loading)
        );

        disposableManager.add(
                movieActionPublishSubject.filter(s -> s.getError() != null && !s.getError().isEmpty())
                        .map(MovieAction::getError)
                        .subscribe(this::showToast)
        );

        disposableManager.add(
                movieActionPublishSubject.filter(c -> c.getListMovies() != null)
                        .map(MovieAction::getListMovies)
                        .subscribe(listMovies -> viewDataBinding.rvMovies.setAdapter(new MainMovieAdapter(listMovies.get(0), listMovies.get(1), listMovies.get(2), listMovies.get(3), listMovies.get(4))))
        );
    }

    @Override
    protected void screenResume() {

    }

    @Override
    protected void screenPause() {

    }

    @Override
    protected void screenStart(@Nullable Bundle saveInstanceState) {

    }

    @Override
    protected void attach(Context context) {

    }

    @NonNull
    @Override
    protected MoviePresenter createPresenter() {
        return getAppComponent().getMainComponent().getMoviePresenter();
    }
}
