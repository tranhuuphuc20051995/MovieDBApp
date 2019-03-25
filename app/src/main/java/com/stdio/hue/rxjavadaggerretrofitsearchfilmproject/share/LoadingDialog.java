package com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.share;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import android.view.Window;
import android.widget.TextView;

import com.stdio.hue.rxjavadaggerretrofitsearchfilmproject.R;

/**
 * Created by TranHuuPhuc on 9/19/18.
 */
public class LoadingDialog extends Dialog {
    private TextView tvTitle;

    public LoadingDialog(@NonNull Context context) {
        super(context, android.R.style.Theme_Black_NoTitleBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loading_block_ui);
        setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        tvTitle = findViewById(R.id.tv_loading);

    }

    public void updateMessage(String mess) {
        tvTitle.setText(mess);
    }
}

