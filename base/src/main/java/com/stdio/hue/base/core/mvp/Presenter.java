package com.stdio.hue.base.core.mvp;

import android.content.Intent;
import android.support.annotation.NonNull;

public interface Presenter {
    interface View {

    }

    void attach(View view);

    void detach();

    void resume();

    void pause();

    void init();

    void destroy();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
