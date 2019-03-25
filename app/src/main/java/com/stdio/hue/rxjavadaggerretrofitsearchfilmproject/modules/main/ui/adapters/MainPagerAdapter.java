package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.adapters;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.fragments.MovieFragment;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.fragments.TVShowFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by TranHuuPhuc on 3/26/19.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MovieFragment.newInstance();
        }
        return TVShowFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
