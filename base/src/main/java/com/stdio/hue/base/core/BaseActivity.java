package com.stdio.hue.base.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import io.reactivex.Observable;

/**
 * Created by TranHuuPhuc on 7/6/18.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    protected void replaceFragment(int view, Fragment fragment, boolean addToStack) {
        if (addToStack) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(view, fragment)
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(view, fragment)
                    .commit();
        }
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public Observable<Boolean> isInternetOn() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
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
