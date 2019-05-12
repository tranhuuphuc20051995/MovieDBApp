package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stdio.hue.data.models.TVShow;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.FragmentTvShowBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieFragment;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.TVShowPresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.actions.TVShowAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters.tvshows.MainTVShowAdapter;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class TVShowFragment extends BaseMovieFragment<TVShowPresenter, FragmentTvShowBinding> implements MainTVShowAdapter.MainTVShowAdapterListener {
    public static TVShowFragment newInstance() {
        Bundle args = new Bundle();
        TVShowFragment fragment = new TVShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tv_show;
    }

    @Override
    protected void init(@Nullable View view) {
        viewDataBinding.rvTvShow.setItemAnimator(new DefaultItemAnimator());
        viewDataBinding.rvTvShow.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        PublishSubject<TVShowAction> tvShowsActionPublishSubject = getAppComponent().getMainComponent().getTVShowState();
        getPresenter().getTVShows();
        disposableManager.add(
                tvShowsActionPublishSubject.map(TVShowAction::isLoading)
                        .subscribe(isLoading -> {
                            if (isLoading) {
                                viewDataBinding.pbLoading.setVisibility(View.VISIBLE);
                            } else {
                                viewDataBinding.pbLoading.setVisibility(View.GONE);
                            }
                        })
        );

        disposableManager.add(
                tvShowsActionPublishSubject.filter(s -> s.getError() != null && !s.getError().isEmpty())
                        .map(TVShowAction::getError)
                        .subscribe(this::showToast)
        );

        disposableManager.add(
                tvShowsActionPublishSubject.filter(c -> c.getListTVShows() != null)
                        .map(TVShowAction::getListTVShows)
                        .subscribe(listMovies -> viewDataBinding.rvTvShow.setAdapter(new MainTVShowAdapter(listMovies.get(0), listMovies.get(1), listMovies.get(2), listMovies.get(3), this)))
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
    protected TVShowPresenter createPresenter() {
        return getAppComponent().getMainComponent().getTVShowPresenter();
    }

    @Override
    public void onItemTVShowClick(TVShow tvShow) {

    }

    @Override
    public void onItemMoreClick(int type) {

    }
}
