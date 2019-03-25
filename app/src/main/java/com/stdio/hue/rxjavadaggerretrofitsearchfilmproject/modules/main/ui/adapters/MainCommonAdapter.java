package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters;

import com.stdio.hue.base.AbsBindingAdapter;
import com.stdio.hue.data.models.Movie;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMoreBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMovieStyleOneBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMovieStyleTwoBinding;

import java.util.List;

import androidx.databinding.ViewDataBinding;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class MainCommonAdapter extends AbsBindingAdapter<ViewDataBinding> {
    public static final int TYPE_NOW = 0;
    public static final int TYPE_COMMON = 1;
    private static final int TYPE_FOOTER = 2;
    private int style;
    private List<Movie> listMovie;

    public MainCommonAdapter(int style, List<Movie> listMovie) {
        super(null);
        this.style = style;
        this.listMovie = listMovie;
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        if (viewType == TYPE_NOW) {
            return R.layout.item_movie_style_one;
        } else if (viewType == TYPE_COMMON) {
            return R.layout.item_movie_style_two;
        } else {
            return R.layout.item_more;
        }
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        if (position == listMovie.size()) {
            ItemMoreBinding itemBind = (ItemMoreBinding) binding;
            itemBind.flMore.setOnClickListener(view -> {
                //Todo show more activity


            });
        } else {
            Movie movie = listMovie.get(position);
            if (binding instanceof ItemMovieStyleOneBinding) {
                ItemMovieStyleOneBinding itemBind = (ItemMovieStyleOneBinding) binding;
                itemBind.setName(movie.getTitle());
                itemBind.setImageUrl(movie.getPosterPath());
            } else {
                ItemMovieStyleTwoBinding itemBind = (ItemMovieStyleTwoBinding) binding;
                itemBind.setName(movie.getTitle());
                itemBind.setImageUrl(movie.getPosterPath());
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == listMovie.size() ? TYPE_FOOTER : style == TYPE_NOW ? TYPE_NOW : TYPE_COMMON;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return listMovie == null ? 0 : listMovie.size() + 1;
    }
}
