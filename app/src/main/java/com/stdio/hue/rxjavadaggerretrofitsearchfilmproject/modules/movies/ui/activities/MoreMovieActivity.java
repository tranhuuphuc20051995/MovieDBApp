package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.activities;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ActivityMoreMovieBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieActivity;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.presenters.MoviePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.actions.MoreMovieAction;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.adapters.MoreMovieAdapter;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by TranHuuPhuc on 3/27/19.
 */
public class MoreMovieActivity extends BaseMovieActivity<MoviePresenter, ActivityMoreMovieBinding> {
    private static final String EXTRA_TYPE_MORE = "extra-type-more";
    public static final int TYPE_NOW = 1;
    public static final int TYPE_POPULAR = 2;
    public static final int TYPE_TOP_RATE = 3;
    public static final int TYPE_UPCOMING = 4;
    public static final int TYPE_RECOMMENDATIONS = 5;
    public static final int TYPE_SIMILAR = 6;

    public static void start(Context context, int type) {
        Intent starter = new Intent(context, MoreMovieActivity.class);
        starter.putExtra(EXTRA_TYPE_MORE, type);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_more_movie;
    }

    private int type;
    private PublishSubject<MoreMovieAction> moreMovieActionPublishSubject;
    private MoreMovieAdapter adapter;

    @Override
    protected void init() {
        if (getIntent() != null) {
            type = getIntent().getIntExtra(EXTRA_TYPE_MORE, 1);
        }
        adapter = new MoreMovieAdapter();
        viewDataBinding.rvMoreMovie.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        viewDataBinding.rvMoreMovie.setAdapter(adapter);
        moreMovieActionPublishSubject = getAppComponent().getMainComponent().getMoreMovieState();
        getData();
        displayData();
    }

    private void displayData() {
        disposableManager.add(
                moreMovieActionPublishSubject.map(MoreMovieAction::isLoading)
                        .subscribe(this::loading)
        );

        disposableManager.add(
                moreMovieActionPublishSubject.filter(s -> s.getError() != null && !s.getError().isEmpty())
                        .map(MoreMovieAction::getError)
                        .subscribe(this::showToast)
        );

        disposableManager.add(
                moreMovieActionPublishSubject.filter(c -> c.getListMovies() != null)
                        .map(MoreMovieAction::getListMovies)
                        .subscribe(listMovies -> adapter.updateData(listMovies))
        );
    }

    private void getData() {
        switch (type) {
            case TYPE_NOW:
                getPresenter().getNowPlayingMoreMovie(1);
                break;
            case TYPE_POPULAR:
                getPresenter().getPopularMoreMovie(1);
                break;
            case TYPE_TOP_RATE:
                getPresenter().getTopRateMoreMovie(1);
                break;
            case TYPE_UPCOMING:
                getPresenter().getUpcomingMoreMovie(1);
                break;
            case TYPE_RECOMMENDATIONS:
                getPresenter().getRecommendations(1, 1);
                break;
            case TYPE_SIMILAR:
            default:
                getPresenter().getSimilarMovies(1, 1);
                break;
        }
    }


    @Override
    protected void startScreen() {

    }

    @Override
    protected void resumeScreen() {

    }

    @Override
    protected void pauseScreen() {

    }

    @Override
    protected void destroyScreen() {

    }

    @NonNull
    @Override
    protected MoviePresenter createPresenter() {
        return getAppComponent().getMainComponent().getMoviePresenter();
    }
}
