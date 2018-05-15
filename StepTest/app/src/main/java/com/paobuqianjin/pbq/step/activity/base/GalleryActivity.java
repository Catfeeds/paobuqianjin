package com.paobuqianjin.pbq.step.activity.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


import com.lwkandroid.imagepicker.data.ImageBean;
import com.paobuqianjin.pbq.step.R;
import com.paobuqianjin.pbq.step.adapter.ImageViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class GalleryActivity extends AppCompatActivity {


    ImageViewAdapter adapter;

    HackyViewPager pager;
    private TextView indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        int position = getIntent().getIntExtra("ID", 0);
        pager = (HackyViewPager) findViewById(R.id.pager);
        ArrayList<ImageBean> list = getIntent().getParcelableArrayListExtra("list");
            adapter = new ImageViewAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(adapter);
        indicator = (TextView) findViewById(R.id.indicator);

        String text = getString(R.string.viewpager_indicator, 1, adapter.getCount());
        indicator.setText(text);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator, arg0 + 1, adapter.getCount());
                indicator.setText(text);
            }
        });
        pager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(-1, R.anim.activity_photo_exit);
    }
}
