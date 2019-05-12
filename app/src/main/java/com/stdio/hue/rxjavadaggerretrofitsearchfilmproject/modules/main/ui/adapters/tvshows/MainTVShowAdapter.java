package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters.tvshows;

import android.content.Context;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stdio.hue.base.AbsBindingAdapter;
import com.stdio.hue.data.models.TVShow;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMainTvShowBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMainTvShowTrendingBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.activities.MoreMovieActivity;

import java.util.List;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class MainTVShowAdapter extends AbsBindingAdapter<ViewDataBinding> {
    private static final int TYPE_TRENDING = 0;
    private static final int TYPE_AIRING_TODAY = 1;
    private static final int TYPE_POPULAR = 2;
    private static final int TYPE_TOP_RATED = 3;
    private List<TVShow> listTrending;
    private List<TVShow> listAiringToday;
    private List<TVShow> listPopular;
    private List<TVShow> listTopRated;
    private MainTVShowAdapterListener listener;

    public MainTVShowAdapter(List<TVShow> listTrending, List<TVShow> listAiringToday, List<TVShow> listPopular, List<TVShow> listTopRated, MainTVShowAdapterListener listener) {
        super(null);
        this.listTrending = listTrending;
        this.listAiringToday = listAiringToday;
        this.listPopular = listPopular;
        this.listTopRated = listTopRated;
        this.listener = listener;
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        if (viewType == TYPE_TRENDING) {
            return R.layout.item_main_tv_show_trending;
        } else {
            return R.layout.item_main_tv_show;
        }
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        Context context = binding.getRoot().getContext();
        if (position == TYPE_TRENDING) {
            ItemMainTvShowTrendingBinding itemBind = (ItemMainTvShowTrendingBinding) binding;
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonTVShowAdapter(MainCommonTVShowAdapter.TYPE_TRENDING, listTrending, listener, MoreMovieActivity.TYPE_NOW));
        } else if (position == TYPE_AIRING_TODAY) {
            ItemMainTvShowBinding itemBind = (ItemMainTvShowBinding) binding;
            itemBind.setTitle("TV Airing Today");
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            itemBind.rvContent.setAdapter(new MainCommonTVShowAdapter(MainCommonTVShowAdapter.TYPE_COMMON, listAiringToday, listener, MoreMovieActivity.TYPE_NOW));
        } else if (position == TYPE_POPULAR) {
            ItemMainTvShowBinding itemBind = (ItemMainTvShowBinding) binding;
            itemBind.setTitle("Popular");
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            itemBind.rvContent.setAdapter(new MainCommonTVShowAdapter(MainCommonTVShowAdapter.TYPE_COMMON, listPopular, listener, MoreMovieActivity.TYPE_NOW));
        } else {
            ItemMainTvShowBinding itemBind = (ItemMainTvShowBinding) binding;
            itemBind.setTitle("Top Rated");
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            itemBind.rvContent.setAdapter(new MainCommonTVShowAdapter(MainCommonTVShowAdapter.TYPE_COMMON, listTopRated, listener, MoreMovieActivity.TYPE_NOW));
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TYPE_TRENDING:
                return TYPE_TRENDING;
            case TYPE_AIRING_TODAY:
                return TYPE_AIRING_TODAY;
            case TYPE_POPULAR:
                return TYPE_POPULAR;
            case TYPE_TOP_RATED:
            default:
                return TYPE_TOP_RATED;
        }
    }

    @Override
    public int getItemCount() {
        return listTrending == null && listAiringToday == null && listPopular == null && listTopRated == null ? 0 : 4;
    }

    public interface MainTVShowAdapterListener {
        void onItemTVShowClick(TVShow tvShow);

        void onItemMoreClick(int type);
    }
}
