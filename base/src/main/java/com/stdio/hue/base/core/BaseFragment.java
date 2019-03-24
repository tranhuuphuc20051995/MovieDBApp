package com.stdio.hue.base.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import io.reactivex.Observable;

/**
 * Created by TranHuuPhuc on 7/6/18.
 */
public class BaseFragment extends Fragment {
    protected void replaceFragment(@IdRes int frameId, Fragment fragment, boolean addToBackTrack) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackTrack) {
            transaction.addToBackStack(this.getClass().getSimpleName());
        }
        transaction.replace(frameId, fragment)
                .commitAllowingStateLoss();
    }

    protected void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public Observable<Boolean> isInternetOn() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean hasNetwork;
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            hasNetwork = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } else {
            hasNetwork = false;
        }

        return Observable.just(hasNetwork);
    }
}
