package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters;

import com.stdio.hue.base.AbsBindingAdapter;
import com.stdio.hue.data.models.Movie;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemSliderBinding;

import java.util.List;

import androidx.databinding.ViewDataBinding;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class MainSliderAdapter extends AbsBindingAdapter<ItemSliderBinding> {
    private List<Movie> listMovie;

    public MainSliderAdapter(List<Movie> listMovie) {
        super(null);
        this.listMovie = listMovie;
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        return R.layout.item_slider;
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        if (binding instanceof ItemSliderBinding) {
            ItemSliderBinding itemBind = (ItemSliderBinding) binding;
            Movie movie = listMovie.get(position);
            itemBind.setName(movie.getTitle());
            itemBind.setImageUrl(movie.getBackdropPath());
        }
    }

    @Override
    public int getItemCount() {
        return listMovie == null ? 0 : listMovie.size();
    }
}
