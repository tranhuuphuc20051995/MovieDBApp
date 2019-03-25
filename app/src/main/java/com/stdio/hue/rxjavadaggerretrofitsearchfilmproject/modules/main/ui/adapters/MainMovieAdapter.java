package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters;

import android.content.Context;

import com.rd.animation.type.AnimationType;
import com.stdio.hue.base.AbsBindingAdapter;
import com.stdio.hue.data.models.Movie;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMainMovieBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ItemMainSliderBinding;

import java.util.List;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class MainMovieAdapter extends AbsBindingAdapter<ViewDataBinding> {
    private static final int TYPE_SLIDER = 0;
    private static final int TYPE_NOW = 1;
    private static final int TYPE_POPULAR = 2;
    private static final int TYPE_TOP = 3;
    private static final int TYPE_UPCOMING = 4;
    private List<Movie> listSlider, listNow, listPopular, listTopRated, listUpcoming;

    public MainMovieAdapter(List<Movie> listSlider, List<Movie> listNow, List<Movie> listPopular, List<Movie> listTopRated, List<Movie> listUpcoming) {
        super(null);
        this.listSlider = listSlider;
        this.listNow = listNow;
        this.listPopular = listPopular;
        this.listTopRated = listTopRated;
        this.listUpcoming = listUpcoming;
    }

    @Override
    protected int getLayoutResourceId(int viewType) {
        if (viewType == TYPE_SLIDER) {
            return R.layout.item_main_slider;
        } else {
            return R.layout.item_main_movie;
        }
    }

    @Override
    public void updateBinding(ViewDataBinding binding, int position) {
        Context context = binding.getRoot().getContext();
        if (position == TYPE_SLIDER) {
            ItemMainSliderBinding itemBind = (ItemMainSliderBinding) binding;
            itemBind.pageIndicator.setCount(listSlider.size());
            itemBind.pageIndicator.setSelection(0);
            itemBind.pageIndicator.setAnimationType(AnimationType.THIN_WORM);
            itemBind.vpSlider.setAdapter(new MainSliderAdapter(listSlider));
            itemBind.vpSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    itemBind.pageIndicator.setSelection(position);
                    super.onPageSelected(position);
                }
            });
        } else if (position == TYPE_NOW) {
            ItemMainMovieBinding itemBind = (ItemMainMovieBinding) binding;
            itemBind.setTitle("Now Playing");
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonAdapter(MainCommonAdapter.TYPE_NOW, listNow));
        } else if (position == TYPE_POPULAR) {
            ItemMainMovieBinding itemBind = (ItemMainMovieBinding) binding;
            itemBind.setTitle("Popular");
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonAdapter(MainCommonAdapter.TYPE_COMMON, listPopular));
        } else if (position == TYPE_TOP) {
            ItemMainMovieBinding itemBind = (ItemMainMovieBinding) binding;
            itemBind.setTitle("Top Rated");
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonAdapter(MainCommonAdapter.TYPE_COMMON, listTopRated));
        } else {
            ItemMainMovieBinding itemBind = (ItemMainMovieBinding) binding;
            itemBind.setTitle("Upcoming");
            itemBind.rvContent.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
            itemBind.rvContent.setAdapter(new MainCommonAdapter(MainCommonAdapter.TYPE_COMMON, listUpcoming));
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TYPE_SLIDER:
                return TYPE_SLIDER;
            case TYPE_NOW:
                return TYPE_NOW;
            case TYPE_POPULAR:
                return TYPE_POPULAR;
            case TYPE_TOP:
                return TYPE_TOP;
            case TYPE_UPCOMING:
            default:
                return TYPE_UPCOMING;
        }
    }

    @Override
    public int getItemCount() {
        return listSlider == null && listNow == null && listPopular == null && listTopRated == null && listUpcoming == null ? 0 : 5;
    }
}
