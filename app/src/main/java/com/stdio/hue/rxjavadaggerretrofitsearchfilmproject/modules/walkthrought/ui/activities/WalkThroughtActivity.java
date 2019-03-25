package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.walkthrought.ui.activities;

import android.content.Context;
import android.content.Intent;

import com.rd.animation.type.AnimationType;
import com.stdio.hue.base.core.mvp.BasePresenter;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.databinding.ActivityWalkthroughtBinding;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.base.BaseMovieActivity;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.main.ui.activities.MainActivity;
import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.modules.walkthrought.ui.adapters.WalkThroughtPagerAdapter;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Created by TranHuuPhuc on 3/25/19.
 */
public class WalkThroughtActivity extends BaseMovieActivity<BasePresenter, ActivityWalkthroughtBinding> {
    public static void start(Context context) {
        Intent starter = new Intent(context, WalkThroughtActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_walkthrought;
    }

    @Override
    protected void init() {
        viewDataBinding.pageIndicator.setCount(3);
        viewDataBinding.pageIndicator.setSelection(0);
        viewDataBinding.pageIndicator.setAnimationType(AnimationType.WORM);
        viewDataBinding.vpWalkthrought.setAdapter(new WalkThroughtPagerAdapter());
        viewDataBinding.btWalkthrought.setText("Next");
        viewDataBinding.btWalkthrought.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_solid_white));
        viewDataBinding.vpWalkthrought.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    viewDataBinding.btWalkthrought.setText("Get Started");
                    viewDataBinding.btWalkthrought.setBackground(ContextCompat.getDrawable(viewDataBinding.getRoot().getContext(), R.drawable.bg_gradient));
                } else {
                    viewDataBinding.btWalkthrought.setText("Next");
                    viewDataBinding.btWalkthrought.setBackground(ContextCompat.getDrawable(viewDataBinding.getRoot().getContext(), R.drawable.bg_solid_white));
                }
                viewDataBinding.pageIndicator.setSelection(position);
                super.onPageSelected(position);
            }
        });

        viewDataBinding.btWalkthrought.setOnClickListener(view -> {
            if (viewDataBinding.vpWalkthrought.getCurrentItem() == 2) {
                MainActivity.start(WalkThroughtActivity.this);
                finish();
            } else {
                viewDataBinding.vpWalkthrought.setCurrentItem(viewDataBinding.vpWalkthrought.getCurrentItem() + 1);
            }
        });
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
    protected BasePresenter createPresenter() {
        return new BasePresenter();
    }
}
