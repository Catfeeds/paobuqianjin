package com.paobuqianjin.pbq.step.view.activity;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.utils.LocalLog;

public class UitetsActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout layout;
    private int index = 0;
    private int[] layer = {R.drawable.log_a, R.drawable.log_b, R.drawable.log_c, R.drawable.log_d};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_test_layout);
        layout = findViewById(R.id.test_ui);
        layout.setOnClickListener(this);
    }

    @TargetApi(21)
    @Override
    public void onClick(View view) {

            Drawable drawable = null;
            if (index % 4 == 0) {
                drawable = getDrawable(layer[0]);

            }
            if (index % 4 == 1) {
                drawable = getDrawable(layer[1]);
            }
            if (index % 4 == 2) {
                drawable = getDrawable(layer[2]);
            }
            if (index % 4 == 3) {
                drawable = getDrawable(layer[3]);
            }
            layout.setBackground(drawable);
            index++;

    }
}
