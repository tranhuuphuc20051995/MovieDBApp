package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters.tvshows;

import androidx.databinding.ViewDataBinding;

import com.stdio.hue.base.AbsBindingAdapter;
import com.stdio.hue.data.models.TVShow;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMoreBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemTvShowMoreBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemTvShowStyleOneBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemTvShowStyleTwoBinding;

import java.util.List;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class MainCommonTVShowAdapter extends AbsBindingAdapter<ViewDataBinding> {
    public static final int TYPE_TRENDING = 0;
    public static final int TYPE_COMMON = 1;
    private static final int TYPE_FOOTER = 2;
    private int style;
    private List<TVShow> listTVShows;
    private MainTVShowAdapter.MainTVShowAdapterListener listener;
    private int type;

    public MainCommonTVShowAdapter(int style, List<TVShow> listTVShows, MainTVShowAdapter.MainTVShowAdapterListener listener, int type) {
        super(null);
        this.style = style;
        this.listTVShows = listTVShows;
        this.listener = listener;
        this.type = type;
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        if (viewType == TYPE_TRENDING) {
            return R.layout.item_tv_show_style_two;
        } else if (viewType == TYPE_COMMON) {
            return R.layout.item_tv_show_style_one;
        } else {
            if (style == TYPE_TRENDING) {
                return R.layout.item_more;
            } else {
                return R.layout.item_tv_show_more;
            }
        }
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        if (position == listTVShows.size()) {
            if (style == TYPE_TRENDING) {
                ItemMoreBinding itemBind = (ItemMoreBinding) binding;
                itemBind.flMore.setOnClickListener(view -> listener.onItemMoreClick(type));
            } else {
                ItemTvShowMoreBinding itemBind = (ItemTvShowMoreBinding) binding;
                itemBind.flMore.setOnClickListener(view -> listener.onItemMoreClick(type));
            }
        } else {
            TVShow tvShow = listTVShows.get(position);
            if (binding instanceof ItemTvShowStyleOneBinding) {
                ItemTvShowStyleOneBinding itemBind = (ItemTvShowStyleOneBinding) binding;
                itemBind.setName(tvShow.getName());
                itemBind.setImageUrl(tvShow.getBackdropPath());
                itemBind.rrvTvShow.setOnClickListener(view -> listener.onItemTVShowClick(tvShow));
            } else {
                ItemTvShowStyleTwoBinding itemBind = (ItemTvShowStyleTwoBinding) binding;
                itemBind.setName(tvShow.getName());
                itemBind.setImageUrl(tvShow.getPosterPath());
                itemBind.rrvTvShow.setOnClickListener(view -> listener.onItemTVShowClick(tvShow));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == listTVShows.size() ? TYPE_FOOTER : style == TYPE_TRENDING ? TYPE_TRENDING : TYPE_COMMON;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return listTVShows == null ? 0 : listTVShows.size() + 1;
    }
}
