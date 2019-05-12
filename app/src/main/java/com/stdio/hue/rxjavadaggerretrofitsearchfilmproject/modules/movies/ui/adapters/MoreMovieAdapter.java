package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.adapters;

import com.stdio.hue.base.AbsBindingAdapter;
import com.stdio.hue.data.models.Movie;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMovieStyleOneBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.adapters.diffutil.MovieDiffUtilCallBack;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by TranHuuPhuc on 3/28/19.
 */
public class MoreMovieAdapter extends AbsBindingAdapter<ViewDataBinding> {
    private List<Movie> items;

    public MoreMovieAdapter() {
        super(null);
    }

    public void updateData(List<Movie> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        MovieDiffUtilCallBack diffCallback = new MovieDiffUtilCallBack(this.items, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.items.clear();
        this.items.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        return R.layout.item_movie_style_one;
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        if (binding instanceof ItemMovieStyleOneBinding) {
            ItemMovieStyleOneBinding itemBind = (ItemMovieStyleOneBinding) binding;
            Movie movie = items.get(position);
            itemBind.setName(movie.getTitle());
            itemBind.setImageUrl(movie.getPosterPath());
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
