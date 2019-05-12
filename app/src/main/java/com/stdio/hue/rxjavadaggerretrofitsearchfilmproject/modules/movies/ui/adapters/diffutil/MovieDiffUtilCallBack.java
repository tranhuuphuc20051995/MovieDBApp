package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.movies.ui.adapters.diffutil;

import com.stdio.hue.data.models.Movie;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by TranHuuPhuc on 3/28/19.
 */
public class MovieDiffUtilCallBack extends DiffUtil.Callback {
    private List<Movie> newList;
    private List<Movie> oldList;

    public MovieDiffUtilCallBack(List<Movie> newList, List<Movie> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId().equals(newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
        return result == 0;
    }
}
