package com.paobuqianjin.pbq.step.view.activity;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;


import com.paobuqianjin.pbq.step.R;


public class UitetsActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = UitetsActivity.class.getSimpleName();
    private RelativeLayout layout;
    private int index = 0;
    private int[] layer = {R.drawable.log_a, R.drawable.log_b, R.drawable.log_c, R.drawable.log_d, R.drawable.jinxuan};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_test_layout);
        layout = (RelativeLayout) findViewById(R.id.test_ui);
        layout.setOnClickListener(this);
        initView();
    }

    private void initView() {
    }


    private String getEmojiStringByUnicode(int unicode) {
        return new String(Character.toChars(unicode));
    }

    @TargetApi(19)
    @Override
    public void onClick(View view) {

        Drawable drawable = null;
        if (index % 5 == 0) {
            drawable = ContextCompat.getDrawable(getApplicationContext(),layer[0]);

        }
        if (index % 5 == 1) {
            drawable = ContextCompat.getDrawable(getApplicationContext(),layer[1]);
        }
        if (index % 5 == 2) {
            drawable = ContextCompat.getDrawable(getApplicationContext(),layer[2]);
        }
        if (index % 5 == 3) {
            drawable = ContextCompat.getDrawable(getApplicationContext(),layer[3]);
        }
        if (index % 5 == 4) {
            drawable = ContextCompat.getDrawable(getApplicationContext(),layer[4]);
        }
        layout.setBackground(drawable);
        index++;

    }
}
